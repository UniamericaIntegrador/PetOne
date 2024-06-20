package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	//@JsonIgnoreProperties("especie")
	@JsonManagedReference(value = "especie-raca")
	private List<Raca>raca;
	
	/*
	@OneToMany(mappedBy = "especie")
	//@JsonIgnoreProperties("especie")
	@JsonManagedReference(value = "especie-paciente")
	private List<Paciente>paciente;
	*/
}
