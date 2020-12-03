package br.gov.sp.fatec.team.domain;

import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;

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
    @JsonView({ View.Team.class })
    private Long id;

    @OneToOne
    @JoinColumn(name = "evaluated_by")
    @JsonView({ View.Team.class, View.Profile.class  })
    private User evaluatedBy;

    @JsonView({ View.Team.class, View.Profile.class })
    private Integer proactivity;

    @JsonView({ View.Team.class, View.Profile.class })
    private Integer collaboration;

    @JsonView({ View.Team.class, View.Profile.class })
    private Integer autonomy;

    @JsonView({ View.Team.class, View.Profile.class })
    private Integer resultsDeliver ;
}
