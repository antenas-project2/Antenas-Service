package br.gov.sp.fatec.representative.controller;

import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.representative.service.RepresentativeService;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("dev/representative")
public class RepresentativeController {

    @Autowired
    private RepresentativeService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.CREATED)
    @JsonView({ View.Representative.class })
    public Representative save (@RequestBody Representative representative, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.path("/dev/representative/activate/").build().toUriString();
        return service.save(representative, url);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @JsonView({ View.Representative.class })
    public Representative findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping
    @JsonView({ View.Representative.class })
    public Representative update(@RequestBody Representative representative) {
        return  service.save(representative);
    }
}
