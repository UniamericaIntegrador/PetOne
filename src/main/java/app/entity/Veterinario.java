package app.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@NotBlank(message = "O endereço do veterinario não pode estar vazio")
	private String endereco;
	
	@OneToMany(mappedBy = "veterinario")
	private List<Procedimento> procedimentos;
}
