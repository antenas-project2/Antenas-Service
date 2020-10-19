package br.gov.sp.fatec.cadi.service;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.repository.CadiRepository;
import br.gov.sp.fatec.cadi.service.CadiService;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.repository.AuthorizationRepository;
import br.gov.sp.fatec.security.service.AuthorizationService;
import br.gov.sp.fatec.utils.commons.SendEmail;
import br.gov.sp.fatec.utils.exception.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CadiServiceImpl implements CadiService {

    @Autowired
    private CadiRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationService authorizationService;

    public Cadi save(Cadi cadi, String url) {

        if (repository.findByEmail(cadi.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        cadi.setActive(false);
        cadi.setPassword(passwordEncoder.encode(cadi.getPassword()));
        cadi.setAuthorizations(new ArrayList<>());

        cadi.getAuthorizations().add(authorizationService.create("ROLE_CADI"));

//        sendEmail.sendMail(cadi.getEmail(), url); todo
        return repository.save(cadi);
    }

    @PreAuthorize("isAuthenticated()")
    public Cadi findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
