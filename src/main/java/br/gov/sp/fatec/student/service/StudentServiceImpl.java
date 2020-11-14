package br.gov.sp.fatec.student.service;

import br.gov.sp.fatec.security.service.AuthorizationService;
import br.gov.sp.fatec.student.domain.AcademicInfo;
import br.gov.sp.fatec.student.domain.ProfessionalInfo;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.repository.StudentRepository;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import br.gov.sp.fatec.utils.commons.SendEmail;
import br.gov.sp.fatec.utils.exception.Exception;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.throwIfUserIsInactive;
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

    @Autowired
    private UserService userService;

    public Student save(Student student, String url) {
        if (repository.findByEmail(student.getEmail()) != null) {
            throw new Exception.CreateUserException();
        }

        student.setActive(false);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setAuthorizations(new ArrayList<>());

        student.getAuthorizations().add(authorizationService.create("ROLE_STUDENT"));

        sendEmail.sendEmail(student.getEmail(), url, null);
        return repository.save(student);
    }

    @PreAuthorize("isAuthenticated()")
    public Student findById(Long id) {
        Student found = repository.findById(id).orElse(null);
        throwIfUserIsNull(found);
        return found;
    }

    @PreAuthorize("isAuthenticated()")
    public List<Student> findAll() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @JsonView({ View.Student.class })
    public Student update(Student user, String url) {
        Student found = (Student) userService.getUserLoggedIn();
        if (!found.getEmail().equals(user.getEmail())) {
            throw new Exception.userInvalidException();
        }

        throwIfUserIsNull(found);
        throwIfUserIsInactive(found);

        found.setName(user.getName());
        found.setPhoto(user.getPhoto());
        found.setRa(user.getRa());
        found.setBiography(user.getBiography());
        found.setCity(user.getCity());
        found.setLinkedin(user.getLinkedin());

        for (AcademicInfo academicInfo : user.getAcademicInfos()) {
            academicInfo.getStudents().add(found);
        }

        for (ProfessionalInfo professionalInfo : user.getProfessionalInfos()) {
            professionalInfo.getStudents().add(found);
        }

        found.setAcademicInfos(user.getAcademicInfos());
        found.setProfessionalInfos(user.getProfessionalInfos());

        if (!user.getEmail().equals(found.getEmail())) {
            sendEmail.sendEmail(user.getEmail(), url, found.getEmail());
        }

        return repository.save(found);
    }
}
