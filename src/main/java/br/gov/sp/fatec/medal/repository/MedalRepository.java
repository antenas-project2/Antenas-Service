package br.gov.sp.fatec.medal.repository;

import br.gov.sp.fatec.medal.domain.Medal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedalRepository extends JpaRepository<Medal, Long>, JpaSpecificationExecutor<Medal> {

    @Query("select m from Medal m " +
            "inner join StudentMedal sm ON m.id = sm.medal.id " +
            "inner join Student s on sm.student.id = s.id " +
            "where sm.student.id = :studentId " +
            "order by sm.date desc")
    List<Medal> findAllByStudentId(Long studentId);
}
