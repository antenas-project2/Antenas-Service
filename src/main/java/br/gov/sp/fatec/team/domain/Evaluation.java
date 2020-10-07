package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.user.domain.User;
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
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Student.class, View.Teacher.class })
    private Long id;

    @OneToOne
    @JoinColumn(name = "evaluated_by")
    @JsonView({ View.Student.class, View.Teacher.class })
    private User evaluatedBy;

    @JsonView({ View.Student.class, View.Teacher.class })
    private int proactivity;

    @JsonView({ View.Student.class, View.Teacher.class })
    private int collaboration;

    @JsonView({ View.Student.class, View.Teacher.class })
    private int autonomy;

    @JsonView({ View.Student.class, View.Teacher.class })
    private int resultsDeliver;
}
