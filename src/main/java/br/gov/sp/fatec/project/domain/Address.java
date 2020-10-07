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
    @JsonView({ View.Cadi.class })
    private Long id;

    @JsonView({ View.Cadi.class })
    private String place;

    @JsonView({ View.Cadi.class })
    private int number;

    @JsonView({ View.Cadi.class })
    private String street;

    @JsonView({ View.Cadi.class })
    private String neighborhood;

    @JsonView({ View.Cadi.class })
    private String city;

    @JsonView({ View.Cadi.class })
    private String zipCode;
}
