package br.gov.sp.fatec.project.domain;

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
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Project.class })
    private Long id;

    @JsonView({ View.Project.class })
    private String place;

    @JsonView({ View.Project.class })
    private int number;

    @JsonView({ View.Project.class })
    private String street;

    @JsonView({ View.Project.class })
    private String neighborhood;

    @JsonView({ View.Project.class })
    private String city;

    @JsonView({ View.Project.class })
    private String zipCode;
}
