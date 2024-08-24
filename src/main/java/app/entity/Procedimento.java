package app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3)
    @NotBlank(message = "O nome do procedimento não pode estar vazio")
    private String nomeProcedimento;

    public Procedimento(long id, String nomeProcedimento) {
        this.id = id;
        this.nomeProcedimento = nomeProcedimento;
    }

    public Procedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }
}
