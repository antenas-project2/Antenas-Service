package br.gov.sp.fatec.user.domain;

import br.gov.sp.fatec.security.domain.Authorization;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class })
    private Long id;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class })
    protected String email;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class })
    protected String password;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class, View.Project.class })
    protected String name;

    protected Boolean active;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class })
    protected String photo;

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class })
    protected String cpf;

    @Transient
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class })
    private String token;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authorization",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authorization_id") })
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class, View.Team.class})
    private List<Authorization> authorizations;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorizations;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Transient
    public Boolean isStudent() {
        for (Authorization authorization : this.getAuthorizations()) {
            if (authorization.getName().equals("ROLE_STUDENT")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public Boolean isTeacher() {
        for (Authorization authorization : this.getAuthorizations()) {
            if (authorization.getName().equals("ROLE_TEACHER")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public Boolean isRepresentative() {
        for (Authorization authorization : this.getAuthorizations()) {
            if (authorization.getName().equals("ROLE_REPRESENTATIVE")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public Boolean isCadi() {
        for (Authorization authorization : this.getAuthorizations()) {
            if (authorization.getName().equals("ROLE_CADI")) {
                return true;
            }
        }
        return false;
    }
}
