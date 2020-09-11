package br.gov.sp.fatec.student.domain;

import br.gov.sp.fatec.medal.domain.Category;
import br.gov.sp.fatec.medal.domain.StudentMedal;
import br.gov.sp.fatec.team.domain.StudentTeam;
import br.gov.sp.fatec.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String ra;

    private String city;

    private String linkedin;

    private String biography;

    @ManyToMany
    @JoinTable(name = "medal_category",
            joinColumns = @JoinColumn(name = "medal_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "student_professional_info",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<ProfessionalInfo> professionalInfos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "student_academic_info",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<AcademicInfo> academicInfos = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<StudentTeam> studentTeamList;

    @OneToMany(mappedBy = "student")
    private List<StudentMedal> studentMedals;
}
