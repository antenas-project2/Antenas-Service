package br.gov.sp.fatec.medal.domain;

import br.gov.sp.fatec.student.domain.Student;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private Long id;

    @ManyToOne
    @MapsId("student_id")
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private Student student;

    @ManyToOne
    @MapsId("medal_id")
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private Medal medal;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private Date date;
}
