package br.gov.sp.fatec.user.dto;

import br.gov.sp.fatec.security.domain.Authorization;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;   
    private String password;
    private List<Authorization> authorizations;
    private String token;
    private Long id;
    private String photo;

    public UserDTO() {}

    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
