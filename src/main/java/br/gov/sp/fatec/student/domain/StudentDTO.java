package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.medal.domain.Medal;
import br.gov.sp.fatec.team.domain.Evaluation;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView({ View.Profile.class })
public class StudentDTO {

    private String ra;
    private String city;
    private String linkedin;
    private String biography;
    private String email;
    private String name;
    private String photo;
    private List<StudentTeam> studentTeam;
    private int completedProjects;
    private List<AcademicInfo> academicInfos;
    private List<ProfessionalInfo> professionalInfos;
    private Evaluation average;
    private List<Medal> medals;
}
