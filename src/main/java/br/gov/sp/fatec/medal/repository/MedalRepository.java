package br.gov.sp.fatec.medal.repository;

import br.gov.sp.fatec.medal.domain.Medal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MedalRepository extends JpaRepository<Medal, Long>, JpaSpecificationExecutor<Medal> {
}
