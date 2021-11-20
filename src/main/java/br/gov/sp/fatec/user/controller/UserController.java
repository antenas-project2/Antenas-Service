package br.gov.sp.fatec.user.controller;

import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.dto.PendingUser;
import br.gov.sp.fatec.user.service.UserService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/user")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.User.class })
    public User getLoggedInfo() {
        return service.getUserLoggedIn();
    }

    @GetMapping(value = "/student/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Student.class })
    public User getStudent(@PathVariable("id") Long id) {
        return service.getStudent(id);
    }

    @GetMapping(value = "/pending-archived-users", produces = APPLICATION_JSON_VALUE)
    public List<PendingUser> findAllDisabledUsers() {
        return service.findAllPendingAndArchivedUsers();
    }

    @PostMapping(value = "/{email}/accept-user")
    public Boolean acceptUser(@PathVariable("email") String email) {
        return service.acceptUser(email);
    }

    @PostMapping(value = "/{email}/decline-user")
    public Boolean declineUser(@PathVariable("email") String email) {
        return service.declineUser(email);
    }

    @GetMapping(value = "/activate/{b64}")
    public void activate(@PathVariable("b64") String b64) {
        service.activate(b64);
    }
}
