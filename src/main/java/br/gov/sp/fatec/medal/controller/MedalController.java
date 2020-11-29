package br.gov.sp.fatec.medal.controller;

import br.gov.sp.fatec.medal.domain.Medal;
import br.gov.sp.fatec.medal.service.MedalService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/medal")
public class MedalController {

    @Autowired
    private MedalService service;

    @GetMapping(produces =  APPLICATION_JSON_VALUE)
    @JsonView({ View.Medal.class })
    public List<Medal> findAll() {
        return service.findAll();
    }
}
