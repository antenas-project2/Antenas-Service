package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.List;

@Data
@JsonView({ View.Team.class })
public class TeamDTO {

    private Long id;
    private String name;
    private String projectUrl;
    private Project project;
    private String communicationLink;
    private List<StudentTeamDTO> studentTeamList;
}
