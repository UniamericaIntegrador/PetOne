package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Especie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "A espécie não pode estar vazia")
	private String nome;

	@OneToMany(mappedBy = "especie")
	@JsonIgnoreProperties("especie")
	private List<Raca> racas;

	// Se não houver relacionamento com Paciente, remova a parte comentada
    /*
    @OneToMany(mappedBy = "especie")
    @JsonIgnoreProperties("especie")
    private List<Paciente> pacientes;
    */
}
