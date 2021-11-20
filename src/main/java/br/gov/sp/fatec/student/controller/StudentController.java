package br.gov.sp.fatec.student.controller;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.domain.StudentDTO;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView({ View.Student.class })
    public Student save (@RequestBody Student student, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.build().toUriString();
        return service.save(student, url);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Student.class })
    public Student findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces =  APPLICATION_JSON_VALUE)
    @JsonView({ View.Student.class })
    public List<Student> findAll() {
        return service.findAll();
    }

    @PutMapping(value = "/update", produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Student.class })
    public Student update(@RequestBody Student student, UriComponentsBuilder uriComponentsBuilder) throws IOException {
        String url = uriComponentsBuilder.build().toUriString();
        return service.update(student, url);
    }

    @JsonView({ View.Profile.class })
    @GetMapping(value = "/profile-info/{id}", produces = APPLICATION_JSON_VALUE)
    public StudentDTO getProfileInfo(@PathVariable("id") Long id) {
        return service.getProfileInfo(id);
    }
}
