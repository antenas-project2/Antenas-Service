package br.gov.sp.fatec.project.domain;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({ View.Cadi.class })
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    @JsonView({ View.Cadi.class })
    private Meeting meeting;

    @JsonView({ View.Cadi.class })
    private String title;

    @JsonView({ View.Cadi.class })
    private String shortDescription;

    @JsonView({ View.Cadi.class })
    private String completeDescription;

    @JsonView({ View.Cadi.class })
    private String technologyDescription;

    @JsonView({ View.Cadi.class })
    private String notes;

    @JsonView({ View.Cadi.class })
    private int progress;

    @JsonView({ View.Cadi.class })
    private java.util.Date updatedAt;

    @CreatedDate
    @JsonView({ View.Cadi.class })
    private ZonedDateTime createdAt;

    @JsonView({ View.Cadi.class })
    private Boolean refused;

    @JsonView({ View.Cadi.class })
    private String reason;

    @JsonView({ View.Cadi.class })
    private Boolean finished;

    @JsonView({ View.Cadi.class })
    private Boolean open;

    @JsonView({ View.Cadi.class })
    private String course;

    @OneToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "id")
    @JsonView({ View.Cadi.class })
    private Cadi approvedBy;

    @OneToOne
    @JoinColumn(name = "finished_by", referencedColumnName = "id")
    @JsonView({ View.Cadi.class })
    private Cadi finishedBy;

    @OneToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @JsonView({ View.Cadi.class })
    private Representative createdBy;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonView({ View.Cadi.class })
    private Teacher teacher;

    @JsonView({ View.Cadi.class })
    private int semester;
}