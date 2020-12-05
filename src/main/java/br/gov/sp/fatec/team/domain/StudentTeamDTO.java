package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonView({ View.Team.class })
public class StudentTeamDTO {

    private Long id;
    private List<Role> role = new ArrayList<>();
    private Team team;
    private Student student;
    private Evaluation evaluation;
}
