package br.gov.sp.fatec.team.repository;

import br.gov.sp.fatec.team.domain.StudentTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentTeamRepository extends JpaRepository<StudentTeam, Long>, JpaSpecificationExecutor<StudentTeam> {

    StudentTeam findByStudentIdAndTeamProjectFinished(Long student, boolean finished);

    List<StudentTeam> findAllByStudentIdAndTeamProjectFinishedOrderByTeamProjectFinishedDateDesc(Long student, boolean finished);
}
