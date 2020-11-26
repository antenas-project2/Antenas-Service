package br.gov.sp.fatec.cadi.service;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.repository.CadiRepository;
import br.gov.sp.fatec.security.service.AuthorizationService;
import br.gov.sp.fatec.utils.commons.SendEmail;
import br.gov.sp.fatec.utils.exception.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

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

    public Cadi save(Cadi user, String url) {

        if (repository.findByEmail(user.getEmail()) != null) {
            throw new Exception.EmailDuplicateException();
        }

        user.setActive(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorizations(new ArrayList<>());

        user.getAuthorizations().add(authorizationService.create("ROLE_CADI"));

        sendEmail.sendEmail(user.getEmail(), url, null);
        return repository.save(user);
    }

    public Cadi update(Cadi user, String url) {
        Cadi found = repository.findById(user.getId()).orElse(null);

        found.setName(user.getName());
        found.setPhoto(user.getPhoto());

        if (!user.getEmail().equals(found.getEmail())) {
            sendEmail.sendEmail(user.getEmail(), url, found.getEmail());
        }

        return repository.save(found);
    }

    @PreAuthorize("isAuthenticated()")
    public Cadi findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
