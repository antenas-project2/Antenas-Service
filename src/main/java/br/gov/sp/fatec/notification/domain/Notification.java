package br.gov.sp.fatec.notification.domain;

import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private Long id;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private String description;
}
