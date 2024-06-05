package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Raca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "A raça não pode estar vazia")
	private String nome;
	
	@NotNull(message = "A especie não pode ser nula")
	@ManyToOne(cascade = CascadeType.MERGE)
	//@JsonIgnoreProperties("raca")
	@JsonBackReference
	private Especie especie;
	
	@OneToMany(mappedBy = "raca")
	//@JsonIgnoreProperties("raca")
	@JsonManagedReference 
	private List<Paciente>paciente;
}
	

