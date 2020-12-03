package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student_team")
public class StudentTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Team.class })
    private Long id;

    @JsonView({ View.Team.class, View.Profile.class })
    @ManyToMany
    @JoinTable(name = "student_role",
            joinColumns = @JoinColumn(name = "student_team_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonView({ View.Profile.class })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonView({ View.Team.class })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Student student;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonView({ View.Team.class, View.Profile.class })
    private Evaluation evaluation;

    public StudentTeam(List<Role> role, Team team, Student student) {
        this.role = role;
        this.team = team;
        this.student = student;
    }
}
