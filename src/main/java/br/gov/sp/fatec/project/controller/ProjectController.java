package br.gov.sp.fatec.project.controller;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.project.service.ProjectService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping("dev/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @JsonView({ View.Project.class })
    public Project create(@RequestBody Project project) {
        return service.save(project);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Project.class })
    public List<Project> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "/update")
    @JsonView({ View.Project.class })
    public Project update(@RequestBody Project project) {
        return service.update(project);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Project.class })
    public Project getById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    @JsonView({ View.Project.class })
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
