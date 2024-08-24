package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O nome do veterinario não pode estar vazio")
    @Pattern(regexp = "^(?=.*\\p{L}.*\\s\\p{L})(?=.*\\p{L}).*$", message = "O nome do veterinario deve conter apenas caracteres alfabéticos e pontos, separados por espaços.")
    private String nome;

    @NotBlank(message = "O CRMV do veterinario não pode estar vazio")
    @Column(unique = true)
    private String crmv;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("veterinarios") // Ajuste conforme o nome da propriedade em Endereco
    private Endereco endereco;

    @OneToMany(mappedBy = "veterinario")
    @JsonIgnoreProperties("veterinario")
    private List<Agendamento> agendamentos;

    @NotBlank(message = "O CPF do tutor não pode estar vazio")
    @CPF
    @Column(unique = true)
    private String cpf;

    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private String role;

    public Veterinario(long id, String nome, String crmv) {
        this.id = id;
        this.nome = nome;
        this.crmv = crmv;
    }
}
