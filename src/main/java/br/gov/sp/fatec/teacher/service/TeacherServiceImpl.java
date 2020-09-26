package br.gov.sp.fatec.teacher.service;

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
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Teacher save(Teacher teacher, String url) {
        if (repository.findByEmail(teacher.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        teacher.setActive(false);
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));

        List<Authorization> authorizations = new ArrayList<>();
        Authorization authorization = new Authorization();
        authorization.setName("TEACHER");
        authorization.setAuthority("TEACHER");
        authorizations.add(authorization);

        teacher.setAuthorizations(authorizations);

//        sendEmail.sendMail(teacher.getEmail(), url); todo
        return repository.save(teacher);
    }

    public List<Teacher> findActive() {
        return repository.findAllByActive(true);
    }

    public Teacher save(Teacher teacher) {
        return repository.save(teacher);
    }

    public List<Teacher> findAll() {
        return repository.findAll();
    }

    public Teacher findById(Long id) {
        Teacher teacher = repository.findById(id).orElse(null);
//        throwIfTeacherIsNull(teacher, id); todo
        return teacher;
    }
}
