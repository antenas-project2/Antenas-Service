package br.gov.sp.fatec.project.domain;

import br.gov.sp.fatec.cadi.domain.Cadi;
import br.gov.sp.fatec.representative.domain.Representative;
import br.gov.sp.fatec.teacher.domain.Teacher;
import br.gov.sp.fatec.user.domain.User;
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
    @JsonView({ View.Project.class })
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meeting_id", referencedColumnName = "id")
    @JsonView({ View.Project.class })
    private Meeting meeting;

    @JsonView({ View.Project.class })
    private String title;

    @JsonView({ View.Project.class })
    private String shortDescription;

    @JsonView({ View.Project.class })
    private String completeDescription;

    @JsonView({ View.Project.class })
    private String technologyDescription;

    @JsonView({ View.Project.class })
    private String notes;

    @JsonView({ View.Project.class })
    private int progress;

    @JsonView({ View.Project.class })
    private java.util.Date updatedAt;

    @CreatedDate
    @JsonView({ View.Project.class })
    private ZonedDateTime createdAt;

    @JsonView({ View.Project.class })
    private Boolean refused;

    @JsonView({ View.Project.class })
    private String reason;

    @JsonView({ View.Project.class })
    private Boolean finished;

    @JsonView({ View.Project.class })
    private Boolean open;

    @JsonView({ View.Project.class })
    private String course;

    @OneToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "id")
    @JsonView({ View.Project.class })
    private User approvedBy;

    @OneToOne
    @JoinColumn(name = "finished_by", referencedColumnName = "id")
    @JsonView({ View.Project.class })
    private Cadi finishedBy;

    @OneToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @JsonView({ View.Project.class })
    private Representative createdBy;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    @JsonView({ View.Project.class })
    private Teacher teacher;

    @JsonView({ View.Project.class })
    private int semester;
}