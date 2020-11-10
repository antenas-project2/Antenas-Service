package br.gov.sp.fatec.user.controller;

import br.gov.sp.fatec.Antena;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(Antena.class);

    @Autowired
    private UserService service;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public User getLoggedInfo() {
        User user = service.getLoggedInfo();
        logger.info("[");
        logger.info(user);
        logger.info("]");
        return user;
    }

    @GetMapping(value = "/activate/{b64}")
    public void activate(@PathVariable("b64") String b64) {
        service.activate(b64);
    }
}
