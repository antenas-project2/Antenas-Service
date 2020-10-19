package br.gov.sp.fatec.security.repository;

import br.gov.sp.fatec.security.domain.Authorization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {

    Authorization findByName(String name);

}