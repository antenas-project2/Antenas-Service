package br.gov.sp.fatec.medal.domain;

import br.gov.sp.fatec.team.domain.StudentTeam;
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
@Table(name = "medal")
@JsonView({ View.Profile.class })
public class Medal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String picture;

    @ManyToMany
    @JoinTable(name = "medal_category",
            joinColumns = @JoinColumn(name = "medal_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "medal")
    private List<StudentMedal> studentMedals;
}
