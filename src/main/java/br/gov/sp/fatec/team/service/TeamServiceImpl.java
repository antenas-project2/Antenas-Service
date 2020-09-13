package br.gov.sp.fatec.team.service;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.team.domain.Team;
import br.gov.sp.fatec.team.repository.StudentTeamRepository;
import br.gov.sp.fatec.team.repository.TeamRepository;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Team> findAll(Long projectId) {
        User user = userService.getUserLoggedIn();

        if (user.getAuthorizations().get(0).getName().equals("STUDENT")) {
            Team team = repository.findBystudentAndProject(projectId, userService.getUserLoggedIn().getId());
            List<Team> teamList = new ArrayList<>();
            if (team != null) {
                teamList.add(team);
            }
            return teamList;
        }

        return repository.findAllByProjectId(projectId);
    }

    public Team save(Team team, String role) {
        Project project = projectService.findById(team.getProject().getId());
        throwIfProjectIsNull(project);
        team.setProject(project);
        Student student = studentService.findById(userService.getUserLoggedIn().getId());
        Team newTeam = repository.save(team);

        StudentTeam studentTeam = new StudentTeam(role, newTeam, student);
        team.setStudentTeamList(new LinkedList<>());
        team.getStudentTeamList().add(studentTeam);

        return studentTeamRepository.save(studentTeam).getTeam();
    }


    public void removeStudent(Long studentId) {
        StudentTeam studentTeam = studentTeamRepository.findByStudentId(studentId);

        if (studentTeam != null) {
            studentTeamRepository.delete(studentTeam);
        }
    }
    
    public Team update(Team team) { // todo - verificar se não veio null
        Team found = repository.findById(team.getId()).orElse(null);
        assert found != null; // todo - criar exception para team

        List<StudentTeam> studentTeams = new LinkedList<>();

        for (StudentTeam studentTeam : team.getStudentTeamList()) { // todo - não deixar cargos iguais ?
            if (studentTeam.getId() != null) {
                studentTeamRepository.findById(studentTeam.getId()).ifPresent(studentTeams::add);
            } else {
                StudentTeam studentTeamExists = studentTeamRepository.findByStudentId(studentTeam.getStudent().getId());

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

        return repository.save(found);
    }
}
