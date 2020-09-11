package br.gov.sp.fatec.medal.domain;

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
    private Long id;

    String name;

    @ManyToMany(mappedBy = "categories")
    private List<Medal> projects = new LinkedList<>();
}
