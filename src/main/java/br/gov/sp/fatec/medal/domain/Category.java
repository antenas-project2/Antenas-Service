package br.gov.sp.fatec.medal.domain;

import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Cadi.class, View.User.class })
    private Long id;

    String name;

    @ManyToMany(mappedBy = "categories")
    @JsonView({ View.Cadi.class, View.User.class })
    private List<Medal> projects = new LinkedList<>();
}
