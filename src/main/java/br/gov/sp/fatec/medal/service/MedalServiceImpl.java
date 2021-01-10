package br.gov.sp.fatec.medal.service;

import br.gov.sp.fatec.medal.domain.Medal;
import br.gov.sp.fatec.medal.domain.StudentMedal;
import br.gov.sp.fatec.medal.repository.MedalRepository;
import br.gov.sp.fatec.medal.repository.StudentMedalRepository;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static br.gov.sp.fatec.utils.exception.Exception.throwIfMedalIsNull;

@Service
@Transactional
public class MedalServiceImpl implements MedalService {

    @Autowired
    private MedalRepository repository;

    @Autowired
    private StudentMedalRepository studentMedalRepository;

    @PreAuthorize("isAuthenticated()")
    @JsonView({ View.Medal.class })
    public List<Medal> findAll() {
        return repository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    public List<Medal> findAllByStudentId(Long id) {
        return repository.findAllByStudentId(id);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public Medal save(Medal medal) {
        medal.setCreationDate(new Date());
        return repository.save(medal);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public Medal update(Medal medal) {
        Medal found = repository.findById(medal.getId()).orElse(null);
        throwIfMedalIsNull(found);

        found.setCategory(medal.getCategory());
        found.setDescription(medal.getDescription());
        found.setName(medal.getName());
        found.setPicture(medal.getPicture());
        found.setColor(medal.getColor());

        return repository.save(found);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public StudentMedal saveStudentMedal(StudentMedal studentMedal) {
        return studentMedalRepository.save(studentMedal);
    }

    public Medal findMedalById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
