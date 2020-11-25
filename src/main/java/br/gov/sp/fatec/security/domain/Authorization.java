package br.gov.sp.fatec.security.domain;

import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "authorization")
public class Authorization implements GrantedAuthority {

    private static final long serialVersionUID = 3078636239920155012L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.User.class })
    private Long id;

    @Column(unique=true, length = 20, nullable = false)
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.User.class })
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public void setAuthority(String authority) {
        this.name = authority;
    }
}
