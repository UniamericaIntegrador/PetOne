package app.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Procedimento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min=3)
	@NotBlank(message = "O nome do procedimento não pode estar vazio")
	private String nomeProcedimento;
	
	@NotNull
	private LocalDate data;
	
	@Size(min=5)
	private String resultado;
	
	@Size(min=7)
	private String diagnostico;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("procedimentos")
	private Veterinario veterinario;

	public Procedimento(long id, String nomeProcedimento) {
        this.id = id;
        this.nomeProcedimento = nomeProcedimento;
    }

    public Procedimento(String nomeProcedimento) {
        this.nomeProcedimento = nomeProcedimento;
    }
}
