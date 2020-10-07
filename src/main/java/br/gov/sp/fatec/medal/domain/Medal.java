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
public class Medal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Cadi.class, View.Representative.class })
    private Long id;

    @JsonView({ View.Cadi.class, View.Representative.class })
    private String name;

    @JsonView({ View.Cadi.class, View.Representative.class })
    private String description;

    @JsonView({ View.Cadi.class, View.Representative.class })
    private String picture;

    @ManyToMany
    @JoinTable(name = "medal_category",
            joinColumns = @JoinColumn(name = "medal_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonView({ View.Cadi.class, View.Representative.class })
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "medal")
    @JsonView({ View.Cadi.class, View.Representative.class })
    private List<StudentMedal> studentMedals;
}
