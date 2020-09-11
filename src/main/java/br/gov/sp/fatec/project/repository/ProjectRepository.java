package br.gov.sp.fatec.project.repository;

import br.gov.sp.fatec.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project>  {

    List<Project> findByCreatedById(Long id);

    @Query("select p from Project p " +
            "inner join Team t on p.id = t.project.id " +
            "inner join StudentTeam st on st.team.id = t.id " +
            "inner join Student s on s.id = st.student.id " +
            "where s.id = ?1")
//            nativeQuery = true)
    List<Project> findByStudentId(Long id);

    List<Project> findByTeacherId(Long id);

    List<Project> findAllByOpen(Boolean open);

}
