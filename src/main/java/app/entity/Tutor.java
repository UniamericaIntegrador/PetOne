package app.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Tutor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome do tutor não pode estar vazio")
	@Pattern(regexp = "^[\\p{L}.]+\\s[\\p{L}.]+$", message = "O nome do tutor deve conter pelo menos dois nomes e apenas caracteres alfabéticos e pontos.")
	private String nome;
	
	@NotBlank
	@CPF
	@Column(unique = true)
	private String cpf;
	
	@NotBlank
	private String endereco;
	
	
	@OneToMany(mappedBy = "tutor")
	private List<Paciente> paciente;
	
}
