package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "professional_info")
public class ProfessionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Student.class })
    private Long id;

    @JsonView({ View.Student.class })
    private String role;

    @JsonView({ View.Student.class })
    private String activitiesPerformed;

    @JsonView({ View.Student.class })
    private String company;

    @JsonView({ View.Student.class })
    private Date start;

    @JsonView({ View.Student.class })
    private Date end;

    @ManyToMany(mappedBy = "professionalInfos")
//    @JsonView({ View.Student.class })
    private List<Student> students = new LinkedList<>();
}
