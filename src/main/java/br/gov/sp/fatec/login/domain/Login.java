package br.gov.sp.fatec.login.domain;

import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Login {

    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private String email;
    
    @JsonView({ View.Cadi.class, View.Representative.class, View.Student.class, View.Teacher.class })
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
