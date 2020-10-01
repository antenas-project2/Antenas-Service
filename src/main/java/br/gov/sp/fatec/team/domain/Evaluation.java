package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.user.domain.User;
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
    private Long id;

    @OneToOne
    @JoinColumn(name = "evaluated_by")
    private User evaluatedBy;

    private int proactivity;

    private int collaboration;

    private int autonomy;

    private int resultsDeliver;
}
