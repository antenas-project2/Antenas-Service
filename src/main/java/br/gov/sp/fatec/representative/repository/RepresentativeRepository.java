package br.gov.sp.fatec.representative.repository;

import br.gov.sp.fatec.representative.domain.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long>, JpaSpecificationExecutor<Representative> {

    List<Representative> findAllByActive(Boolean active);

    Representative findByEmail(String email);
}
