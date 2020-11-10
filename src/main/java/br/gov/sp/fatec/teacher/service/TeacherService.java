package br.gov.sp.fatec.teacher.service;

import br.gov.sp.fatec.teacher.domain.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher save(Teacher teacher, String url);

    Teacher save(Teacher teacher);

    List<Teacher> findAll();

    Teacher findById(Long id);

    List<Teacher> findActive();

    Teacher update(Teacher user, String url);
}
