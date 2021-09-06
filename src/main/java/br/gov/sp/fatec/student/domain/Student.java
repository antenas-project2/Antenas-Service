package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.medal.domain.StudentMedal;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "id")
public class Student extends User {

    @JsonView({ View.Student.class, View.User.class })
    private String ra;

    @JsonView({ View.Student.class, View.User.class })
    private String city;

    @JsonView({ View.Student.class, View.User.class })
    private String linkedin;

    @JsonView({ View.Student.class, View.User.class })
    private String biography;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_professional_info",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professional_info_id"))
    @JsonView({ View.Student.class, View.User.class, View.Profile.class })
    private List<ProfessionalInfo> professionalInfos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_academic_info",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "academic_info_id"))
    @JsonView({ View.Student.class, View.User.class, View.Profile.class })
    private List<AcademicInfo> academicInfos = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonView({ View.Student.class, View.User.class, View.Team.class, })
    private List<StudentMedal> studentMedals;
}
