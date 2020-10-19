package br.gov.sp.fatec.teacher.service;

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
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationService authorizationService;

    public Teacher save(Teacher teacher, String url) {
        if (repository.findByEmail(teacher.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        teacher.setActive(false);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setAuthorizations(new ArrayList<>());

        teacher.getAuthorizations().add(authorizationService.create("ROLE_TEACHER"));

//        sendEmail.sendMail(teacher.getEmail(), url); todo
        return repository.save(teacher);
    }

    @PreAuthorize("isAuthenticated()")
    public List<Teacher> findActive() {
        return repository.findAllByActive(true);
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

    @PreAuthorize("isAuthenticated()")
    public List<Teacher> findAll() {
        return repository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    public Teacher findById(Long id) {
        Teacher teacher = repository.findById(id).orElse(null);
//        throwIfTeacherIsNull(teacher, id); todo
        return teacher;
    }
}
