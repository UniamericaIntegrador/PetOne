package app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class LogDTO {

    private int id;
    private String origem;
    private String acao;
    private Date timestamp;
    private String descricao;
    private Long usuario;

}
