package app.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgendamentoDTO {

    private long id;
    private LocalDate data;
    private String nome;
    private String resultado;
    private String diagnostico;
    private long veterinario;
    private long paciente_id;

    public AgendamentoDTO(long id, LocalDate data, String nome, String resultado, String diagnostico, long veterinario, long paciente_id) {
    }
}
