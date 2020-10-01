package br.gov.sp.fatec.team.controller;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.team.domain.Team;
import br.gov.sp.fatec.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/team")
public class TeamController {

    @Autowired
    private TeamService service;

    @GetMapping(path = "/{projectId}", produces = APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<Team> findAll(@PathVariable("projectId") Long projectId) {
        return service.findAll(projectId);
    }


    @PostMapping(path = "/{role}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    public Team save (@RequestBody Team team,
                      @PathVariable("role") String role) {
        return service.save(team, role);
    }

    @PutMapping
    public Team update(@RequestBody Team team) {
        return service.update(team);
    }

    @DeleteMapping(path = "/{studentId}")
    public void delete(@PathVariable("studentId") Long studentId) {
        service.removeStudent(studentId);
    }
}
