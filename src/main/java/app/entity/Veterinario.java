package app.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	
	/*
	@NotBlank(message = "O endereço do veterinario não pode estar vazio")
	private String endereco;
	*/
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "endereco_id")
    //@JsonIgnoreProperties("veterinario")
	@JsonBackReference(value = "endereco-veterinario")
    private Endereco endereco;
    
	
	@OneToMany(mappedBy = "veterinario")
	@JsonIgnoreProperties("veterinario")
	//@JsonManagedReference
	private List<Procedimento> procedimentos;
	
	public Veterinario(long id, String nome, String crmv) {
        this.id = id;
        this.nome = nome;
        this.crmv = crmv;
    }
}
