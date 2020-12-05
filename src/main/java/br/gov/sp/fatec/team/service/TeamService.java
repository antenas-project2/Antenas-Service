package br.gov.sp.fatec.team.service;

import br.gov.sp.fatec.team.domain.Role;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.team.domain.Team;
import br.gov.sp.fatec.team.domain.TeamDTO;

import java.util.List;

public interface TeamService {

    List<TeamDTO> findAll(Long projectId);

    Team save(Team team);

    void removeStudent(Long studentId);

    Team update(Team team);

    List<Role> getRoles();

    Team updateStudentTeam(StudentTeam studentTeam);

    List<StudentTeam> findAllByStudent(Long studentId);

    void evaluate(List<Team> teams);
}
