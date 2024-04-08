package app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Veterinario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O nome do cliente não pode estar vazio")
	@Pattern(regexp = "^[\\p{L}.]+\\s[\\p{L}.]+$", message = "O nome do cliente deve conter pelo menos dois nomes e apenas caracteres alfabéticos e pontos.")
	private String nome;
	
	@NotBlank
	@Column(unique = true)
	private String crmv;
	
	@NotBlank
	private String endereco;
}
