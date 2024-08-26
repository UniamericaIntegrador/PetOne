package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
public class Raca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//@NotBlank(message = "A raça não pode estar vazia")
	private String nome;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("racas") // Ajuste conforme o nome da propriedade em Especie
	private Especie especie;

	@OneToMany(mappedBy = "raca")
	@JsonIgnoreProperties("raca")
	private List<Paciente> pacientes;
}
