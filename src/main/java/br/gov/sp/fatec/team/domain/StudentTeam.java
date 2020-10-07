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
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private Long id;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private String role;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private Student student;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "student_team_evaluation",
            joinColumns = @JoinColumn(name = "evaluation_id"),
            inverseJoinColumns = @JoinColumn(name = "student_team_id"))
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private List<Evaluation> evaluations = new ArrayList<>();

    public StudentTeam(String role, Team team, Student student) {
        this.role = role;
        this.team = team;
        this.student = student;
    }
}
