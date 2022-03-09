package com.acc.escola.model.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlunoRespDTO {

    private Long id;

    private String nome;

    private String alunoVLMensalidade;

    private String alunoTipoBolsa;


}
