package br.gov.sp.fatec.user.service;

import br.gov.sp.fatec.cadi.service.CadiService;
import br.gov.sp.fatec.representative.service.RepresentativeService;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.repository.AuthorizationRepository;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.teacher.service.TeacherService;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.dto.PendingUser;
import br.gov.sp.fatec.user.dto.UserDTO;
import br.gov.sp.fatec.user.repository.UserRepository;
import br.gov.sp.fatec.utils.exception.Exception;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import javassist.NotFoundException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.gov.sp.fatec.utils.exception.Exception.throwIfUserIsNull;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private CadiService cadiService;

    @Autowired
    private TeacherService teacherService;

    public List<User> search(String login) {
        return userRepository.findByNameContainsIgnoreCase(login);
    }

    public User getStudent(Long id) {
        return studentService.findById(id);
//        User user = userRepository.findById(id).orElse(null);
//        throwIfUserIsNull(user);

//        return user.isStudent() ? user : null;
    }

    @PreAuthorize("isAuthenticated()")
    public List<PendingUser> findAllPendingAndArchivedUsers() {
        if (!this.isLoggedUserEligibilityToModifyOtherUser()) {
            throw new Exception.UserRetrievingInvalidException();
        }

        if (this.isLoggedUserTeacher()) {
            return userRepository.findAllPendingAndArchivedStudentsAndTeachers();
        }

        return userRepository.findAllPendingAndArchivedUsers();
    }

    @PreAuthorize("isAuthenticated()")
    public Boolean acceptUser(String email) {
        if (!this.isLoggedUserEligibilityToModifyOtherUser()) {
            throw new Exception.UserInvalidException();
        }

        User foundUser = userRepository.findByEmail(email);
        throwIfUserIsNull(foundUser);

        if (this.isLoggedUserTeacher() && !(foundUser.isStudent() || foundUser.isTeacher())) {
            throw new Exception.UserInvalidException();
        }

        unarchiveUser(foundUser);
        return activeUser(foundUser);
    }

    @PreAuthorize("isAuthenticated()")
    public Boolean declineUser(String email) {
        if (!this.isLoggedUserEligibilityToModifyOtherUser()) {
            throw new Exception.UserInvalidException();
        }

        User foundUser = userRepository.findByEmail(email);
        throwIfUserIsNull(foundUser);

        if (this.isLoggedUserTeacher() && !(foundUser.isStudent() || foundUser.isTeacher())) {
            throw new Exception.UserInvalidException();
        }

        return archiveUser(foundUser);
    }

    public Boolean isLoggedUserEligibilityToModifyOtherUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_STUDENT") || r.getAuthority().equals("ROLE_REPRESENTATIVE"));
    }

    public Boolean isLoggedUserTeacher() {
        return this.verifyRole("ROLE_TEACHER");
    }

    public Boolean verifyRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals(role));
    }

    public Boolean unarchiveUser(User user) {
        user.setArchived(false);
        userRepository.save(user);
        return true;
    }

    public Boolean archiveUser(User user) {
        user.setArchived(true);
        userRepository.save(user);
        return true;
    }

    public Boolean activeUser(User user) {
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @PreAuthorize("isAuthenticated()")
    public User search(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public User save(User user) {
        List<Authorization> authorizations = user.getAuthorizations();

        if(!authorizations.isEmpty()) {
            for(Authorization aut: authorizations) {
                if(aut.getId() == null && authorizationRepository.findByName(aut.getName()) == null) {
                    authorizationRepository.save(aut);
                }
            }
        }
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User getUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User user = findByEmail(authentication.getName());
            return user;
        }
        return null;
    }

    public User activate(String b64) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(new String(Base64.getDecoder().decode(b64)));
            User found;
            if (jsonObject.has("oldEmail")) {
                found = userRepository.findByEmail(jsonObject.get("oldEmail").toString());
                throwIfUserIsNull(found);
                found.setEmail(jsonObject.get("email").toString());
            } else {
                found = userRepository.findByEmail(jsonObject.get("email").toString());
            }

            assert found != null;
            found.setActive(true);
            return userRepository.save(found);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

