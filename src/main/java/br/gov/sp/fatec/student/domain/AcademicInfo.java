package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.project.domain.Project;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import liquibase.pro.packaged.D;
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
@Table(name = "academic_info")
public class AcademicInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Student.class, View.User.class })
    private Long id;

    @JsonView({ View.Student.class, View.User.class })
    private String course;

    @JsonView({ View.Student.class, View.User.class })
    private String institution;

    @JsonView({ View.Student.class, View.User.class })
    private Date start;

    @JsonView({ View.Student.class, View.User.class })
    private Date end;

    @ManyToMany(mappedBy = "academicInfos")
//    @JsonView({ View.Student.class, View.User.class })
    private List<Student> students = new LinkedList<>();
}
