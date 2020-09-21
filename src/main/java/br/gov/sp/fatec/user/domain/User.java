package br.gov.sp.fatec.user.domain;

import br.gov.sp.fatec.security.domain.Authorization;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    protected String email;

    protected String password;

    protected String name;

    protected Boolean active;

    @Lob
    protected Blob photo;

    protected String cpf;

    @Transient
    private String token;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authorization",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authorization_id") })
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
            if (authorization.getName().equals("STUDENT")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public Boolean isTeacher() {
        for (Authorization authorization : this.getAuthorizations()) {
            if (authorization.getName().equals("TEACHER")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public Boolean isRepresentative() {
        for (Authorization authorization : this.getAuthorizations()) {
            if (authorization.getName().equals("REPRESENTATIVE")) {
                return true;
            }
        }
        return false;
    }

    @Transient
    public Boolean isCadi() {
        for (Authorization authorization : this.getAuthorizations()) {
            if (authorization.getName().equals("CADI")) {
                return true;
            }
        }
        return false;
    }
}
