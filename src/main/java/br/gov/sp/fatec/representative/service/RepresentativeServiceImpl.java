package br.gov.sp.fatec.representative.service;

import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.representative.repository.RepresentativeRepository;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.repository.AuthorizationRepository;
import br.gov.sp.fatec.security.service.AuthorizationService;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.repository.TeacherRepository;
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
public class RepresentativeServiceImpl implements RepresentativeService{
    @Autowired
    private RepresentativeRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationService authorizationService;

    public Representative save(Representative representative, String url) {
        if (repository.findByEmail(representative.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        representative.setActive(false);
        representative.setPassword(passwordEncoder.encode(representative.getPassword()));
        representative.setAuthorizations(new ArrayList<>());

        representative.getAuthorizations().add(authorizationService.create("ROLE_REPRESENTATIVE"));

        sendEmail.sendMail(representative.getEmail(), url);

        return repository.save(representative);
    }

    public Representative save(Representative entrepreneur) {
        return repository.save(entrepreneur);
    }

    @PreAuthorize("isAuthenticated()")
    public Representative findById(Long id) {
        Representative found = repository.findById(id).orElse(null);
//        throwIfRepresentativeIsNull(found, id); todo

        return found;
    }
}
