package br.gov.sp.fatec.representative.service;

import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.representative.repository.RepresentativeRepository;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.repository.TeacherRepository;
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
public class RepresentativeServiceImpl implements RepresentativeService{
    @Autowired
    private RepresentativeRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Representative save(Representative representative, String url) {
        if (repository.findByEmail(representative.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        representative.setActive(false);
        representative.setPassword(passwordEncoder.encode(representative.getPassword()));

        List<Authorization> authorizations = new ArrayList<>();
        Authorization authorization = new Authorization();
        authorization.setName("REPRESENTATIVE");
        authorization.setAuthority("REPRESENTATIVE");
        authorizations.add(authorization);

        representative.setAuthorizations(authorizations);

//        sendEmail.sendMail(representative.getEmail(), url); todo

        return repository.save(representative);
    }

    public Representative save(Representative entrepreneur) {
        return repository.save(entrepreneur);
    }

    public Representative findById(Long id) {
        Representative found = repository.findById(id).orElse(null);
//        throwIfRepresentativeIsNull(found, id); todo

        return found;
    }
}
