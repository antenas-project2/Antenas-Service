package br.gov.sp.fatec.medal.domain;

import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
    @JsonView({ View.Medal.class, View.Team.class })
    private Long id;

    @JsonView({ View.Profile.class, View.Medal.class, View.Team.class  })
    private String name;

    @JsonView({ View.Profile.class, View.Medal.class, View.Team.class  })
    private String description;

    @JsonView({ View.Profile.class, View.Medal.class, View.Team.class  })
    private String picture;

    @JsonView({ View.Medal.class, View.Team.class  })
    private Date creationDate;

    @JsonView({ View.Medal.class, View.Team.class  })
    private String color;

    @ManyToMany
    @JoinTable(name = "medal_category",
            joinColumns = @JoinColumn(name = "medal_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonView({ View.Profile.class, View.Medal.class, View.Team.class })
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "medal")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<StudentMedal> studentMedals;
}

