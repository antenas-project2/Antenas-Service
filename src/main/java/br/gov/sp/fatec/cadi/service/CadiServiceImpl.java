package br.gov.sp.fatec.cadi.service;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.repository.CadiRepository;
import br.gov.sp.fatec.cadi.service.CadiService;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.utils.commons.SendEmail;
import br.gov.sp.fatec.utils.exception.Exception;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Cadi save(Cadi cadi, String url) {

        if (repository.findByEmail(cadi.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        cadi.setActive(false);

        List<Authorization> authorizations = new ArrayList<>();
        Authorization authorization = new Authorization();
        authorization.setName("CADI");
        authorization.setAuthority("CADI");
        authorizations.add(authorization);

        cadi.setAuthorizations(authorizations);

        cadi.setPassword(passwordEncoder.encode(cadi.getPassword()));
//        sendEmail.sendMail(cadi.getEmail(), url); todo
        return repository.save(cadi);
    }

    public Cadi findById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
