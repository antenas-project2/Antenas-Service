package br.gov.sp.fatec.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PendingUser {
    private Long id;
    private String name;
    private String email;
    private Boolean active;
    private Boolean archived;

    public PendingUser(Long id, String name, String email, Boolean active, Boolean archived) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
        this.archived = archived;
    }
}

