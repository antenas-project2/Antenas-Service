package br.gov.sp.fatec.representative.domain;

import br.gov.sp.fatec.user.domain.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "representative")
@PrimaryKeyJoinColumn(name = "id")
public class Representative extends User {

    private String company;

    private String telephone;
}
