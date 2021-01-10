package br.gov.sp.fatec.medal.service;

import br.gov.sp.fatec.medal.domain.Medal;
import br.gov.sp.fatec.medal.domain.StudentMedal;

import java.util.List;

public interface MedalService {

    List<Medal> findAll();

    List<Medal> findAllByStudentId(Long id);

    Medal save(Medal medal);

    Medal update(Medal medal);

    StudentMedal saveStudentMedal(StudentMedal studentMedal);

    Medal findMedalById(Long id);
}
