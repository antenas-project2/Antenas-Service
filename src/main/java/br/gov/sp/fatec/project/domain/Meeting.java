package br.gov.sp.fatec.project.domain;

import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "meeting")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Project.class })
    private Long id;

    @Column(name = "date")
    @JsonView({ View.Project.class })
    private java.util.Date chosenDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonView({ View.Project.class })
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="meeting_date",
            joinColumns=@JoinColumn(name="meeting_id"),
            inverseJoinColumns=@JoinColumn(name="date_id"))
    @JsonView({ View.Project.class })
    private List<Date> possibleDate;
}
