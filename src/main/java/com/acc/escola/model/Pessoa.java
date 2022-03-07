package com.acc.escola.model;

import com.acc.escola.enums.Sexo;
import com.acc.escola.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity




public class Pessoa implements Serializable {
    private static final long serialVersionUID = 7670534790770756526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "Nome", length = 45, nullable=false)
    private String nome;
    @NotEmpty(message = "O campo nome não pode ser em branco")

    @Column( name = "CPF", length = 14)
    private String cpf;
    private Sexo sexo;
    private Tipo tipo;

    public Sexo getSexo() {
        return Sexo.toEnum(sexo);
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Tipo getTipo() {

        return Tipo.toEnum(tipo);
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
