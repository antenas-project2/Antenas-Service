package br.gov.sp.fatec.medal.domain;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student_medal")
public class StudentMedal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("student_id")
    private Student student;

    @ManyToOne
    @MapsId("medal_id")
    private Medal medal;

    private Date date;
}
