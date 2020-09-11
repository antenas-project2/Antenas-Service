package br.gov.sp.fatec.medal.service;

import br.gov.sp.fatec.medal.domain.Medal;
import br.gov.sp.fatec.medal.repository.MedalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MedalService {

    @Autowired
    private MedalRepository repository;

    public List<Medal> findAll() {
        return repository.findAll();
    }
}
