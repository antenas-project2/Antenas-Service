package br.gov.sp.fatec.student.domain;

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
    private Long id;

    private String role;

    private String activitiesPerformed;

    private Date start;

    private Date end;

    @ManyToMany(mappedBy = "professionalInfos")
    private List<Student> students = new LinkedList<>();
}
