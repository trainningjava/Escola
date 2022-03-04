package com.acc.escola.model;

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

    @Column( name = "Sexo", nullable=false)
//    @NotEmpty(message = "O campo nome não pode ser em branco")
//    @Enumerated(EnumType.STRING)
    private Integer sexo;

    public String getSexo() {
        return Sexo.toEnum(sexo).getlabel();
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo.getCod();
    }

        @Column( name = "Tipo")
    private Integer tipo;
    public String getTipo() {
        return Tipo.toEnum(tipo).getlabel();
    }

    public void getTipo(Tipo tipo) {
        this.tipo = tipo.getCod();
    }

}
