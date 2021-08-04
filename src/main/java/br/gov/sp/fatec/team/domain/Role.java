package br.gov.sp.fatec.team.domain;

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
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Team.class })
    private Long id;

    @JsonView({ View.Team.class, View.Profile.class })
    private String name;
}
