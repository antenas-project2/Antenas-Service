package br.gov.sp.fatec.project.domain;

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
    private Long id;

    @Column(name = "date")
    private java.util.Date chosenDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="meeting_date",
            joinColumns=@JoinColumn(name="meeting_id"),
            inverseJoinColumns=@JoinColumn(name="date_id"))
    private List<Date> possibleDate;

    @OneToOne
    private Project project;
}
