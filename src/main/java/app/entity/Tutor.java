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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@Pattern(regexp = "^(?=.*\\p{L}.*\\s\\p{L})(?=.*\\p{L}).*$", message = "O nome do tutor deve conter apenas caracteres alfabéticos e pontos, separados por espaços.")
	private String nome;
	
	@NotBlank(message = "O CPF do tutor não pode estar vazio")
	@CPF
	@Column(unique = true)
	private String cpf;
	
	@NotNull
	private int idade;
	
	/*
	@NotBlank(message = "O endereço do tutor não pode estar vazio")
	private String endereco;
	*/
	
	@ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "endereco_id")
    @JsonIgnoreProperties("tutor")
    private Endereco endereco;
	
	@OneToMany(mappedBy = "tutor")
	@JsonIgnoreProperties("tutor")
	private List<Paciente> paciente;
	
    // Construtor correspondente aos parâmetros usados nos testes
    public Tutor(long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }
}
