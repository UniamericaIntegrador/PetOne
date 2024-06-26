package app.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
	
	@NotBlank(message = "O nome do paciente não pode estar vazio")
	private String nome;
	
	@NotBlank(message = "O nome do raca não pode estar vazio")
	private String raca;
	
	@NotBlank(message = "O nome da especie não pode estar vazio")
	private String especie;
	
	@NotNull
	@PastOrPresent
	private LocalDate dataNascimento;

	/*
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "especie_id")
	//@JsonIgnoreProperties("paciente")
	@JsonBackReference(value = "especie-paciente")
	private Especie especie;
	*/
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "raca_id")
	@JsonIgnoreProperties("paciente")
    private Raca raca;
	*/
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnoreProperties("paciente")
	//@JsonBackReference
	private Tutor tutor;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "paciente_procedimento")
	private List<Procedimento> procedimentos;

	public Paciente(long id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;   
    }
}
