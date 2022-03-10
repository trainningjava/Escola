package com.acc.escola.model.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PessoaRespDTO {

    private Long id;

    private String nome;

    private String cpf;

    private String sexo;

    private String tipo;
}
