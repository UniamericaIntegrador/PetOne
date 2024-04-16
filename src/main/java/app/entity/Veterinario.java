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
<<<<<<< HEAD
	@Pattern(regexp = "^(?=.*\\p{L}.*\\s\\p{L})(?=.*\\p{L}).*$", message = "O nome do veterinario deve conter apenas caracteres alfabéticos e pontos, separados por espaços.")
=======
	@Pattern(regexp = "^(?:[\\p{L}.]+\\s?)+$", message = "O nome do tutor deve conter apenas caracteres alfabéticos e pontos, separados por espaços.")
>>>>>>> refs/remotes/origin/master
	private String nome;
	
	@NotBlank(message = "O CRMV do veterinario não pode estar vazio")
	@Column(unique = true)
	private String crmv;
	
	@NotBlank(message = "O endereço do veterinario não pode estar vazio")
	private String endereco;

	public Veterinario(long id, String nome, String crmv, String endereco) {
        this.id = id;
        this.nome = nome;
        this.crmv = crmv;
        this.endereco = endereco;
    }
	
	@OneToMany(mappedBy = "veterinario")
	private List<Procedimento> procedimentos;
}
