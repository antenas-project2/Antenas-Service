package br.gov.sp.fatec.representative.service;

import br.gov.sp.fatec.representative.domain.Representative;

public interface RepresentativeService {

    Representative save(Representative representative, String url);

    Representative save(Representative entrepreneur);

    Representative findById(Long id);
}
