package br.gov.sp.fatec.team.repository;

import br.gov.sp.fatec.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

    @Query("select t from Team t " +
            "inner join StudentTeam st ON t.id = st.team.id " +
            "where t.project.id = :projectId and st.student.id = :studentId")
    Team findBystudentAndProject(@Param("projectId") Long projectId,
                                 @Param("studentId") Long studentId);

    List<Team> findAllByProjectId(Long projectId);
}
