package com.acc.escola.model.responses;

import com.acc.escola.enums.Sexo;
import com.acc.escola.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

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
