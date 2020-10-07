package br.gov.sp.fatec.user.controller;

import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    public User getLoggedInfo() {
        return service.getLoggedInfo();
    }
}
