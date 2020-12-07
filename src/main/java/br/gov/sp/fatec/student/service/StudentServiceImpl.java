package br.gov.sp.fatec.student.service;

import br.gov.sp.fatec.medal.service.MedalService;
import br.gov.sp.fatec.security.service.AuthorizationService;
import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.domain.StudentDTO;
import br.gov.sp.fatec.student.repository.StudentRepository;
import br.gov.sp.fatec.team.domain.Evaluation;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.team.service.TeamService;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.throwIfUserIsInactive;
import static br.gov.sp.fatec.utils.exception.Exception.throwIfUserIsNull;

@Service
@Transactional
public class StudentServiceImpl implements  StudentService {

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

    @Autowired
    private TeamService teamService;

    @Autowired
    private MedalService medalService;

    public Student save(Student student, String url) {
        if (repository.findByEmail(student.getEmail()) != null) {
            throw new Exception.EmailDuplicateException();
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
        User user = userService.getUserLoggedIn();

        List<Student> students = repository.findAll();
        if (user.getAuthorizations().get(0).getName().equals("ROLE_STUDENT")) {
            students.remove(user);
        }
        return students;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @JsonView({ View.Student.class, View.User.class })
    public Student update(Student user, String url) throws IOException {
        Student found = (Student) userService.getUserLoggedIn();
        if (!found.getEmail().equals(user.getEmail())) {
            throw new Exception.UserInvalidException();
        }

        throwIfUserIsNull(found);
        throwIfUserIsInactive(found);

        found.setName(user.getName());
        found.setPhoto(user.getPhoto());
        found.setRa(user.getRa());
        found.setBiography(user.getBiography());
        found.setCity(user.getCity());
        found.setLinkedin(user.getLinkedin());
        found.setAcademicInfos(user.getAcademicInfos());
        found.setProfessionalInfos(user.getProfessionalInfos());

        if (!user.getEmail().equals(found.getEmail())) {
            sendEmail.sendEmail(user.getEmail(), url, found.getEmail());
        }

        return repository.save(found);
    }

    public StudentDTO getProfileInfo(Long id) {
        Student found = (Student) userService.findById(id);
        StudentDTO student = new StudentDTO();

        student.setBiography(found.getBiography());
        student.setCity(found.getCity());
        student.setEmail(found.getEmail());
        student.setLinkedin(found.getLinkedin());
        student.setPhoto(found.getPhoto());
        student.setStudentTeam(teamService.findAllByStudent(found.getId()));
        student.setCompletedProjects(student.getStudentTeam().size());
        student.setAcademicInfos(found.getAcademicInfos());
        student.setProfessionalInfos(found.getProfessionalInfos());
        student.setAverage(this.getAverage(student.getStudentTeam()));
        student.setMedals(medalService.findAllByStudentId(found.getId()));
        student.setName(found.getName());

        return student;
    }

    private Evaluation getAverage(List<StudentTeam> studentTeamList) {
        Evaluation evaluationAverage = new Evaluation();

        int qty = studentTeamList.size();
        int proactivity = 0;
        int collaboration = 0;
        int autonomy = 0;
        int resultsDeliver = 0;

        for (StudentTeam studentTeam : studentTeamList) {
            if (studentTeam.getEvaluation() != null) {
                proactivity += studentTeam.getEvaluation().getProactivity();
                collaboration += studentTeam.getEvaluation().getCollaboration();
                autonomy += studentTeam.getEvaluation().getAutonomy();
                resultsDeliver += studentTeam.getEvaluation().getResultsDeliver();
            }
        }
        evaluationAverage.setAutonomy(autonomy / qty);
        evaluationAverage.setCollaboration(collaboration / qty);
        evaluationAverage.setProactivity(proactivity / qty);
        evaluationAverage.setResultsDeliver(resultsDeliver / qty);
        return evaluationAverage;
    }
}
