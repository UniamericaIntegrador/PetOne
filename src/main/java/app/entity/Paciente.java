package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String especie;
	
	@NotBlank
	private String dataNascimento;
	
	@NotBlank
	private String raca;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("pacientes")
	private Tutor tutor;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "paciente_procedimento")
	private List<Procedimento> procedimentos;

	public Paciente(long id, String nome, String especie, String dataNascimento, String raca) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
    }
}
