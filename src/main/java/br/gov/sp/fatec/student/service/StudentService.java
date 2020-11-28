package br.gov.sp.fatec.student.service;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.student.domain.StudentDTO;

import java.util.List;

public interface StudentService {

    Student save(Student student, String url);

    Student findById(Long id);

    List<Student> findAll();

    Student update(Student user, String url);

    StudentDTO getProfileInfo(Long id);
}
