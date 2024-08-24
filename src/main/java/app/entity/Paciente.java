package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O nome do paciente não pode estar vazio")
	private String nome;

	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "raca_id")
	@JsonIgnoreProperties("pacientes")
	private Raca raca;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties("pacientes")
	private Tutor tutor;

	@ManyToOne
	@JoinColumn(name = "especie_id")
	@JsonIgnoreProperties("pacientes")
	private Especie especie; // Adicionando o relacionamento com Especie

	@OneToMany(mappedBy = "paciente")
	@JsonManagedReference
	private List<Agendamento> agendamentos;
}