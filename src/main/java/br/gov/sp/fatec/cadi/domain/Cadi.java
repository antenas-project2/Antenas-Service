package br.gov.sp.fatec.cadi.domain;

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
@Table(name = "cadi")
@PrimaryKeyJoinColumn(name = "id")
public class Cadi extends User {

    private String position;
}
