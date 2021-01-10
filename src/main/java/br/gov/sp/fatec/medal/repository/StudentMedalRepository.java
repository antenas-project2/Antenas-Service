package br.gov.sp.fatec.medal.repository;

import br.gov.sp.fatec.medal.domain.Medal;
import br.gov.sp.fatec.medal.domain.StudentMedal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentMedalRepository extends JpaRepository<StudentMedal, Long>, JpaSpecificationExecutor<StudentMedal> {
}
