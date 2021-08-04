package br.gov.sp.fatec.project.service;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.project.domain.Meeting;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.repository.ProjectRepository;
import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.representative.service.RepresentativeService;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.service.TeacherService;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.*;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_REPRESENTATIVE')")
    public Project save(Project project) {
        Representative found = representativeService.findById(userService.getUserLoggedIn().getId());
        System.out.println(found);
        throwIfUserIsNull(found);
        throwIfUserIsInactive(found);
        project.setCreatedBy(found);
        project.setUpdatedAt(new Date());
        project.setCreatedAt(ZonedDateTime.now());
        project.setProgress(2);
        project.setOpen(false);
        project.setRefused(false);
        project.setFinished(false);
        return initializeObject(repository.save(project));
    }

    @PreAuthorize("isAuthenticated()")
    public Project findById(Long id) {
        return initializeObject(repository.findById(id).orElse(null));
    }

    @PreAuthorize("isAuthenticated()")
    public List<Project> findAll() {
        Long id = userService.getUserLoggedIn().getId();
        String authorization = userService.search(id).getAuthorizations().get(0).getName();

        List<Project> projects = new ArrayList<>();

        if (authorization.equals("ROLE_REPRESENTATIVE")) {
            projects = repository.findByCreatedByIdOrderByUpdatedAtDesc(id);
        } else if (authorization.equals("ROLE_STUDENT")) {
            projects = repository.findAllByOpenOrderByUpdatedAtDesc(true);

            for (Project project : repository.findByStudentId(id)) {
                if (!projects.contains(project)) {
                    projects.add(project);
                }
            }
        } else if (authorization.equals("ROLE_CADI")) {
            projects = repository.findAllByOrderByUpdatedAtDesc();
        } else if (authorization.equals("ROLE_TEACHER")) {
            projects = repository.findByTeacherIdOrderByUpdatedAtDesc(id);
        }
        return initializeObjects(projects);
    }

    @PreAuthorize("isAuthenticated()")
    public Project update(Project project) {
        Project projectFound = projectRepository.findById(project.getId()).orElse(null);
        throwIfProjectIsNull(projectFound);

        User user = userService.getUserLoggedIn();
        throwIfUserIsNull(user);

        switch (user.getAuthorizations().get(0).getName()) {
            case "ROLE_REPRESENTATIVE":
                projectFound.setTitle(project.getTitle());
                projectFound.setShortDescription(project.getShortDescription());
                projectFound.setCompleteDescription(project.getCompleteDescription());
                projectFound.setTechnologyDescription(project.getTechnologyDescription());
                projectFound.setNotes(project.getNotes());
                if (projectFound.getMeeting() != null) {
                    projectFound.getMeeting().setChosenDate(project.getMeeting().getChosenDate());
                }
                break;

            case "ROLE_CADI":
                if (project.getMeeting() != null && project.getMeeting().getAddress() != null) {
                    projectFound.setMeeting(project.getMeeting());
                }
                if (project.getRefused() != null && project.getRefused()) {
                    projectFound.setRefused(project.getRefused());
                    projectFound.setReason(project.getReason());
                }
                else {
                    project.setApprovedBy(user);
                    projectFound.setCourse(project.getCourse());
                    projectFound.setSemester(project.getSemester());
                    projectFound.setOpen(project.getOpen());

                    if (project.getFinished() != null && projectFound.getFinished() != null) {
                        projectFound.setFinished(project.getFinished());

                        Cadi cadi = (Cadi) user;
                        projectFound.setFinishedBy(cadi);
                    }

                    if (project.getApprovedBy() != null && projectFound.getApprovedBy() != null) {
                        Cadi cadi = (Cadi) user;
                        projectFound.setApprovedBy(cadi);
                    }

                    if (project.getTeacher() != null) {
                        Teacher teacher = teacherService.findById(project.getTeacher().getId());
                        throwIfUserIsNull(teacher);
                        throwIfUserIsInactive(teacher);
                        projectFound.setTeacher(teacher);
                    }
                }
                break;

            case "ROLE_TEACHER":
                projectFound.setOpen(project.getOpen());
                projectFound.setFinished(project.getFinished());
                if (project.getFinished()) {
                    project.setOpen(false);
                    project.setFinishedDate(new Date());
//                    project.setFinishedBy(); todo - mudar pra PROFESOR
                }
                projectFound.setShortDescription(project.getShortDescription());
                projectFound.setCompleteDescription(project.getCompleteDescription());
                projectFound.setTechnologyDescription(project.getTechnologyDescription());
                break;
        }
        projectFound.setProgress(getProgress(projectFound));
        projectFound.setUpdatedAt(new Date());
        return initializeObject(repository.save(projectFound));
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public Project closeProject (Project project) {
        Project projectFound = projectRepository.findById(project.getId()).orElse(null);
        throwIfProjectIsNull(projectFound);

        User user = userService.getUserLoggedIn();
        throwIfUserIsNull(user);

        projectFound.setOpen(false);
        projectFound.setProgress(8);

        return initializeObject(repository.save(projectFound));
    }

    public void closeAndFinishProject(Project project) {
        Project projectFound = projectRepository.findById(project.getId()).orElse(null);
        throwIfProjectIsNull(projectFound);

        User user = userService.getUserLoggedIn();
        throwIfUserIsNull(user);

        projectFound.setOpen(false);
        projectFound.setProgress(9);
        projectFound.setFinished(true);

        repository.save(projectFound);
    }

    private int getProgress (Project project) {
        if (project.getRefused() == null || !project.getRefused()) {
            if (project.getProgress() == 2 && project.getCompleteDescription() == null && project.getTechnologyDescription() == null) {
                return 3;
            } else if (project.getCompleteDescription() != null && project.getTechnologyDescription() != null && project.getProgress() == 3) {
                return 4;
            } if (project.getProgress() == 4 && project.getCompleteDescription() != null && project.getTechnologyDescription() != null) {
                return 5;
            } else if (project.getProgress() == 5 && project.getMeeting() != null && project.getMeeting().getPossibleDate().size() > 0 && project.getMeeting().getChosenDate() != null) {
                return 6;
            } else if (project.getProgress() == 6) {
                return 7;
            } else if (project.getProgress() == 8 && project.getFinished()) {
                return 9;
            }
        }
        return project.getProgress();
    }

    public List<Project> initializeObjects(List<Project> projects) {
        for (Project project : projects) {
            project = initializeObject(project);
        }
        return projects;
    }

    public Project initializeObject(Project project) {
        if (project.getMeeting() == null) {
            project.setMeeting(new Meeting("initialize"));
        }
        return project;
    }

    @PreAuthorize("hasAnyRole('ROLE_REPRESENTATIVE')")
    public void delete(Long projectId) {
        Project project = findById(projectId);
        throwIfProjectIsNull(project);

        if (project.getRefused() != null && project.getRefused()) {
            repository.delete(project);
        } else {
            throw new ProjectCannotBeDeletedException();
        }
    }
}
