package br.gov.sp.fatec.project.service;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.service.CadiService;
import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.repository.ProjectRepository;
import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.representative.service.RepresentativeService;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.service.TeacherService;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.*;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CadiService cadiService;

    public Project save(Project project) {
        Representative found = representativeService.findById(userService.getUserLoggedIn().getId());
        throwIfUserIsNull(found);
        throwIfUserIsInactive(found);
        project.setCreatedBy(found);

        project.setCreatedAt(ZonedDateTime.now());
        project.setProgress(2);
        project.setOpen(false);
        return repository.save(project);
    }

    public Project findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Project> findAll() {
        Long id = userService.getUserLoggedIn().getId();
        String authorization = userService.search(id).getAuthorizations().get(0).getName();

        List<Project> projects = new ArrayList<>();

        if (authorization.equals("REPRESENTATIVE")) {
            projects = repository.findByCreatedById(id);
        } else if (authorization.equals("STUDENT")) {
            projects = repository.findAllByOpen(true);

            for (Project project : repository.findByStudentId(id)) {
                if (!projects.contains(project)) {
                    projects.add(project);
                }
            }
        } else if (authorization.equals("CADI")) {
            projects = repository.findAll();
        } else if (authorization.equals("TEACHER")) {
            projects = repository.findByTeacherId(id);
        }
        return projects;
    }

    public Project update(Project project) {
        Project projectFound = projectRepository.findById(project.getId()).orElse(null);
        throwIfProjectIsNull(projectFound);

        Long userId = userService.getUserLoggedIn().getId();
        User user = userService.findById(userId);
        throwIfUserIsNull(user);

        switch (user.getAuthorizations().get(0).getName()) {
            case "REPRESENTATIVE":
                projectFound.setTitle(project.getTitle());
                projectFound.setShortDescription(project.getShortDescription());
                projectFound.setCompleteDescription(project.getCompleteDescription());
                projectFound.setTechnologyDescription(project.getTechnologyDescription());
                projectFound.setNotes(project.getNotes());
                projectFound.getMeeting().setChosenDate(project.getMeeting().getChosenDate());
                break;

            case "CADI":
                projectFound.setMeeting(project.getMeeting());
                projectFound.setRefused(project.getRefused());
                projectFound.setReason(project.getReason());
                projectFound.setCourse(project.getCourse());
                projectFound.setSemester(project.getSemester());
                projectFound.setOpen(project.getOpen());

                if (project.getFinished() != null && projectFound.getFinished() != null) {
                    projectFound.setFinished(project.getFinished());

                    Cadi cadi = cadiService.findById(userId);
                    projectFound.setFinishedBy(cadi);
                }

                if (project.getApprovedBy() != null && projectFound.getApprovedBy()  != null) {
                    Cadi cadi = cadiService.findById(userId);
                    projectFound.setApprovedBy(cadi);
                }

                if (project.getTeacher() != null) {
                    Teacher teacher = teacherService.findById(project.getTeacher().getId());
                    throwIfUserIsNull(teacher);
                    throwIfUserIsInactive(teacher);
                    projectFound.setTeacher(teacher);
                }

                break;

            case "TEACHER":
                projectFound.setOpen(project.getOpen());
                break;
        }

        projectFound.setProgress(getProgress(projectFound));
        projectFound.setUpdatedAt(new Date());

        return repository.save(projectFound);
    }

    private int getProgress (Project project) {
        if (project.getProgress() == 2 && project.getCompleteDescription() == null && project.getTechnologyDescription() == null) {
            return 3;
        } else if (project.getCompleteDescription() != null && project.getTechnologyDescription() != null && project.getProgress() == 3) {
            return 4;
        } if (project.getProgress() == 4 && project.getCompleteDescription() != null && project.getTechnologyDescription() != null) {
            return 5; // todo - criar o 5.1, 5.2,
        } else if (project.getProgress() == 5 && project.getMeeting() != null && project.getMeeting().getPossibleDate().size() > 0 && project.getMeeting().getChosenDate() != null) {
            return 6; // todo - criar o 6.1
        } else if (project.getProgress() == 6 ) { // todo - verificar se tem a url
            return 7;
        } else if (project.getProgress() == 7 && !project.getOpen() ) {
            return 8;
        } else {
            return project.getProgress();
        }
    }
}
