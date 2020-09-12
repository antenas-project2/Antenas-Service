package br.gov.sp.fatec.team.service;

import br.gov.sp.fatec.team.domain.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAll(Long projectId);

    Team save(Team team, String role);

    void removeStudent(Long studentId);

    Team update(Team team);
}
