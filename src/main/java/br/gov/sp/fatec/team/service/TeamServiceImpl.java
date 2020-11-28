package br.gov.sp.fatec.team.service;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.team.domain.Role;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.team.domain.Team;
import br.gov.sp.fatec.team.repository.RoleRepository;
import br.gov.sp.fatec.team.repository.StudentTeamRepository;
import br.gov.sp.fatec.team.repository.TeamRepository;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.*;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private StudentTeamRepository studentTeamRepository;

    @Autowired
    private TeamRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("isAuthenticated()")
    public List<Team> findAll(Long projectId) {
        User user = userService.getUserLoggedIn();

        if (user.getAuthorizations().get(0).getName().equals("ROLE_STUDENT")) {
            Team team = repository.findBystudentAndProject(projectId, userService.getUserLoggedIn().getId());
            List<Team> teamList = new ArrayList<>();
            if (team != null) {
                teamList.add(team);
            }

            return teamList;
        }

        return repository.findAllByProjectId(projectId);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public Team save(Team team) {
        Student student = (Student) userService.getUserLoggedIn();

        StudentTeam studentTeamExists = studentTeamRepository.findByStudentIdAndTeamProjectFinished(student.getId(), false);

        if (studentTeamExists != null) {
            throw new studentAlreadyInTeamException();
        }

        Project project = projectService.findById(team.getProject().getId());
        throwIfProjectIsNull(project);
        team.setProject(project);
        Team newTeam = repository.save(team);

        List<Role> roles = new ArrayList<>();
        for (Role role: team.getRoles()) {
            roles.add(roleRepository.findById(role.getId()).orElse(null));
        }

        StudentTeam studentTeam = new StudentTeam(roles, newTeam, student);
        team.setStudentTeamList(new LinkedList<>());
        team.getStudentTeamList().add(studentTeam);

        return studentTeamRepository.save(studentTeam).getTeam();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public void removeStudent(Long studentTeamId) {
        studentTeamRepository.findById(studentTeamId).ifPresent(studentTeam -> studentTeamRepository.delete(studentTeam));
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    public Team update(Team team) { // todo - verificar se não veio null
        Team found = repository.findById(team.getId()).orElse(null);
        assert found != null; // todo - criar exception para team

        List<StudentTeam> studentTeams = new LinkedList<>();

        for (StudentTeam studentTeam : team.getStudentTeamList()) {
            if (studentTeam.getId() != null) {
                studentTeamRepository.findById(studentTeam.getId()).ifPresent(studentTeams::add);
            } else {
                StudentTeam studentTeamExists = studentTeamRepository.findByStudentIdAndTeamProjectFinished(studentTeam.getStudent().getId(), false);

                if (studentTeamExists != null) {
                    throw new studentAlreadyInTeamException();
                }

                Student student = studentService.findById(studentTeam.getStudent().getId());
                throwIfUserIsNull(student);

                StudentTeam studentTeam1 = new StudentTeam();
                studentTeam1.setStudent(student);
                studentTeam1.setTeam(found);
                studentTeam1.setRole(studentTeam.getRole());
                studentTeams.add(studentTeamRepository.save(studentTeam1));
            }
        }

        found.setName(team.getName());
        found.setStudentTeamList(studentTeams);
        found.setProjectUrl(team.getProjectUrl());
        found.setCommunicationLink(team.getCommunicationLink());

        for (StudentTeam studentTeamFound : found.getStudentTeamList()) {
            for (StudentTeam studentTeam : team.getStudentTeamList()) {
                if (studentTeamFound.getId().equals(studentTeam.getId()) && studentTeam.getEvaluations().size() > 0) {
                    studentTeam.getEvaluations().get(0).setEvaluatedBy(userService.getUserLoggedIn());
                    studentTeamFound.getEvaluations().add(studentTeam.getEvaluations().get(0));
                }
            }
        }

        return repository.save(found);
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    public Team updateStudentTeam(StudentTeam studentTeam) {
        StudentTeam found = studentTeamRepository.findById(studentTeam.getId()).orElse(null);

        assert found != null;
        found.setRole(studentTeam.getRole());
        return studentTeamRepository.save(found).getTeam();

    }
}

