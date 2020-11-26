package br.gov.sp.fatec.team.repository;

import br.gov.sp.fatec.team.domain.StudentTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentTeamRepository extends JpaRepository<StudentTeam, Long>, JpaSpecificationExecutor<StudentTeam> {

    StudentTeam findByStudentIdAndTeamProjectId(Long studentId, Long projectId);

    StudentTeam findAllByStudentId(Long studentId);

    List<StudentTeam> findAllByStudentIdAndTeamProjectFinished(Long studentId, boolean finished);
}
