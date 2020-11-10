package br.gov.sp.fatec.cadi.service;

import br.gov.sp.fatec.cadi.domain.Cadi;

public interface CadiService {

    Cadi save(Cadi cadi, String url);

    Cadi findById(Long id);

    Cadi update(Cadi user, String url);
}
