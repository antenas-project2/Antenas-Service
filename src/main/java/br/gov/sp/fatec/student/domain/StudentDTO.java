package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.team.domain.StudentTeam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String ra;
    private String city;
    private String linkedin;
    private String biography;
    private String email;
    private String name;
    private String photo;
    private List<StudentTeam> studentTeam;
}
