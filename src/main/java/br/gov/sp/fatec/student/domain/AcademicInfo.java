package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.project.domain.Project;
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
    private Long id;

    private String course;

    private String institution;

    private Date start;

    private Date end;

    @ManyToMany(mappedBy = "academicInfos")
    private List<Student> students = new LinkedList<>();
}
