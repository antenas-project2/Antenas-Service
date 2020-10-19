package br.gov.sp.fatec.security.service;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthorizationService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    public Authorization create(String name) {
        Authorization authorization = authorizationRepository.findByName(name);
        if(authorization == null) {
            authorization = new Authorization();
            authorization.setName(name);
            return authorizationRepository.save(authorization);
        }
        return authorization;
    }

    public List<Authorization> list() {
        List<Authorization> authorizations = new ArrayList<>();
        for (Authorization authorization: authorizationRepository.findAll()) {
            authorizations.add(authorization);
        }
        return authorizations;
    }

    public Authorization findById(Long authorizationId) {
        return authorizationRepository.findById(authorizationId).orElse(null);
    }
}
