package br.gov.sp.fatec.project.domain;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.teacher.domain.Teacher;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    private Meeting meeting;

    private String title;

    private String shortDescription;

    private String completeDescription;

    private String technologyDescription;

    private String notes;

    private int progress;

    private java.util.Date updatedAt;

    @CreatedDate
    private ZonedDateTime createdAt;

    private Boolean refused;

    private String reason;

    private Boolean finished;

    private Boolean open;

    private String course;

    @OneToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "id")
    private Cadi approvedBy;

    @OneToOne
    @JoinColumn(name = "finished_by", referencedColumnName = "id")
    private Cadi finishedBy;

    @OneToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private Representative createdBy;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    private int semester;
}