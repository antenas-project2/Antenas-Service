package br.gov.sp.fatec.student.controller;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.service.StudentService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(value = HttpStatus.CREATED)
    @JsonView({ View.Student.class })
    public Student save (@RequestBody Student student) {
        return service.save(student);
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
}
