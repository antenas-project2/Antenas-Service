package br.gov.sp.fatec.user.service;

import br.gov.sp.fatec.cadi.service.CadiService;
import br.gov.sp.fatec.representative.service.RepresentativeService;
import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.security.repository.AuthorizationRepository;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.teacher.service.TeacherService;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.repository.UserRepository;
import br.gov.sp.fatec.utils.exception.Exception;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.throwIfUserIsNull;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

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
        return repository.findByNameContainsIgnoreCase(login);
    }

    @PreAuthorize("isAuthenticated()")
    public List<User> findAllDisabledUsers() {
        return repository.findAllNotActive();
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @PreAuthorize("isAuthenticated()")
    public User search(Long id) {
        return repository.findById(id).orElse(null);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
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
        return repository.save(user);
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
                found = repository.findByEmail(jsonObject.get("oldEmail").toString());
                throwIfUserIsNull(found);
                found.setEmail(jsonObject.get("email").toString());
            } else {
                found = repository.findByEmail(jsonObject.get("email").toString());
            }

            assert found != null;
            found.setActive(true);
            return repository.save(found);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

