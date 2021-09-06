package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Team.class })
    private Long id;

    @JsonView({ View.Team.class })
    private String name;

    @JsonView({ View.Team.class, View.Profile.class })
    private String projectUrl;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @JsonView({ View.Profile.class })
    private Project project;

    @JsonView({ View.Team.class })
    private String communicationLink;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    @JsonView({ View.Team.class })
    private List<StudentTeam> studentTeamList;

    @Transient
    private List<Role> roles;
}
