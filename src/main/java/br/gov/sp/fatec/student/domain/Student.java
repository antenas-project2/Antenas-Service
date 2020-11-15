package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.medal.domain.Category;
import br.gov.sp.fatec.medal.domain.StudentMedal;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonView({ View.Student.class })
    private String ra;

    @JsonView({ View.Student.class })
    private String city;

    @JsonView({ View.Student.class })
    private String linkedin;

    @JsonView({ View.Student.class })
    private String biography;

    @ManyToMany
    @JoinTable(name = "medal_category",
            joinColumns = @JoinColumn(name = "medal_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonView({ View.Student.class })
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_professional_info",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professional_info_id"))
    @JsonView({ View.Student.class })
    private List<ProfessionalInfo> professionalInfos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_academic_info",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "academic_info_id"))
    @JsonView({ View.Student.class })
    private List<AcademicInfo> academicInfos = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<StudentTeam> studentTeamList;

    @OneToMany(mappedBy = "student")
    @JsonView({ View.Student.class })
    private List<StudentMedal> studentMedals;
}
