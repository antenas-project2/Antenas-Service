package br.gov.sp.fatec.cadi.controller;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.cadi.service.CadiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dev/cadi")
public class CadiController {

    @Autowired
    CadiService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    private Cadi create(@RequestBody Cadi cadi, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.build().toUriString();
        return service.save(cadi, url);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Cadi findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping(value = "/update", produces = APPLICATION_JSON_VALUE)
    public Cadi update(@RequestBody Cadi cadi, UriComponentsBuilder uriComponentsBuilder) {
        String url = uriComponentsBuilder.build().toUriString();
        return service.update(cadi, url);
    }
}
