package br.gov.sp.fatec.representative.domain;

import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.utils.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "representative")
@PrimaryKeyJoinColumn(name = "id")
public class Representative extends User {

    @JsonView({ View.Representative.class })
    private String company;

    @JsonView({ View.Representative.class })
    private String telephone;
}
