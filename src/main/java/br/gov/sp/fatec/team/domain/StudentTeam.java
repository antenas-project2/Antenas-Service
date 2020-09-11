package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    private String role;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToMany
    @JoinTable(name = "student_team_evaluation",
            joinColumns = @JoinColumn(name = "evaluation_id"),
            inverseJoinColumns = @JoinColumn(name = "student_team_id"))
    private List<Evaluation> evaluations = new ArrayList<>();

    public StudentTeam(String role, Team team, Student student) {
        this.role = role;
        this.team = team;
        this.student = student;
    }
}
