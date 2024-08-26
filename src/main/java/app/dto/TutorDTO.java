package app.dto;

import app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TutorDTO {

    private long id;
    private String nome;
    private String email;
    private String role;
    private Endereco endereco;

}
