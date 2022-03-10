package com.acc.escola.model.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlunoRespDTO {

    private Long id;

    private String pessoa;

    private String turma;

    private String disciplina;

    private String bolsa;

    private Double mensalidade;

}
