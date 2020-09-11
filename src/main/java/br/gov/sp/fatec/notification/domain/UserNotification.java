package br.gov.sp.fatec.notification.domain;

import br.gov.sp.fatec.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_notification")
public class UserNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Notification notification;

    @OneToMany(mappedBy = "id", targetEntity = User.class)
    private List<User> user;

    private Boolean viewed;

    private Date date;

}
