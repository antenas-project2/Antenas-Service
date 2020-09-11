package br.gov.sp.fatec.teacher.repository;

import br.gov.sp.fatec.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    List<Teacher> findAllByActive(Boolean active);

    Teacher findByEmail(String email);
}