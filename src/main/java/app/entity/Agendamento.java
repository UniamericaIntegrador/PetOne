package app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @FutureOrPresent
    private LocalDate data;

    @NotNull
    private String nome;

    @Size(min = 5)
    private String resultado;

    @Size(min = 7)
    private String diagnostico;

    private Long id_paciente;

    private String paciente_nome;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("agendamentos")
    private Veterinario veterinario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonBackReference
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "procedimento_id")
    @JsonIgnoreProperties("agendamentos")
    private Procedimento procedimento;

}
