package br.gov.sp.fatec.teacher.domain;

import br.gov.sp.fatec.user.domain.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends User {
}
