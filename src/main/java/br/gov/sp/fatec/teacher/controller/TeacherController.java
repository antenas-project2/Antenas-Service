package br.gov.sp.fatec.teacher.controller;

import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.service.TeacherService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/teacher")
public class TeacherController {
    @Autowired
    private TeacherService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @JsonView({ View.Teacher.class })
    public Teacher create (@RequestBody Teacher teacher, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.build().toUriString();
        return service.save(teacher, url);
    }

    @GetMapping(produces =  APPLICATION_JSON_VALUE)
    @JsonView({ View.Teacher.class })
    public List<Teacher> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/active")
    @JsonView({ View.Teacher.class })
    public List<Teacher> findActive() {
        return service.findActive();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Teacher.class })
    public Teacher findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value = "/update", produces = APPLICATION_JSON_VALUE)
    public Teacher update(@RequestBody Teacher teacher, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.build().toUriString();
        return service.update(teacher, url);
    }

}
