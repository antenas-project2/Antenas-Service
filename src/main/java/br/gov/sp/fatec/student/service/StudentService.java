package br.gov.sp.fatec.student.service;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.repository.StudentRepository;
import br.gov.sp.fatec.utils.commons.SendEmail;
import br.gov.sp.fatec.utils.exception.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.throwIfUserIsNull;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student save(Student student) {
        if (repository.findByEmail(student.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        student.setActive(false);
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        List<Authorization> authorizations = new ArrayList<>();
        Authorization authorization = new Authorization();
        authorization.setName("STUDENT");
        authorization.setAuthority("STUDENT");
        authorizations.add(authorization);

        student.setAuthorizations(authorizations);

//        sendEmail.sendMail(student.getEmail(), "student"); todo - descomentar
        return repository.save(student);
    }

    public Student findById(Long id) {
        Student found = repository.findById(id).orElse(null);
        throwIfUserIsNull(found);
        return found;
    }

    public List<Student> findAll() {
        return repository.findAll();
    }
}
