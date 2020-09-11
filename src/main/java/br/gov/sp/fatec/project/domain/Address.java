package br.gov.sp.fatec.project.domain;

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
    private Long id;

    private String place;

    private int number;

    private String street;

    private String neighborhood;

    private String city;

    private String zipCode;
}
