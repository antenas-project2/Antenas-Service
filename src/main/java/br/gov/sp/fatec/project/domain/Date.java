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
@Table(name = "date")
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Cadi.class })
    private Long id;

    @Column(name = "date")
    @JsonView({ View.Cadi.class })
    private java.util.Date dateTime;
}

