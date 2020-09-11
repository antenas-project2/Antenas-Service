package br.gov.sp.fatec.teacher.controller;

import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.teacher.service.TeacherService;
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
    public Teacher create (@RequestBody Teacher teacher, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.path("/dev/cadi/activate/").build().toUriString();
        return service.save(teacher, url);
    }

    @GetMapping(produces =  APPLICATION_JSON_VALUE)
    public List<Teacher> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/active")
    public List<Teacher> findActive() {
        return service.findActive();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Teacher findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

}
