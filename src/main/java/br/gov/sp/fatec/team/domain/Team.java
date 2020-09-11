package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.project.domain.Project;
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
    private Long id;

    private String name;

    private String projectUrl;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    private String communicationLink;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<StudentTeam> studentTeamList;
}
