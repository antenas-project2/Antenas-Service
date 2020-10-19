package br.gov.sp.fatec.student.service;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.repository.AuthorizationRepository;
import br.gov.sp.fatec.security.service.AuthorizationService;
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
public class StudentServiceImpl implements  StudentService{

    @Autowired
    private StudentRepository repository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationService authorizationService;

    public Student save(Student student) {
        if (repository.findByEmail(student.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        student.setActive(false);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setAuthorizations(new ArrayList<>());

        student.getAuthorizations().add(authorizationService.create("CADI"));

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
