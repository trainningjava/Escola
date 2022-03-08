package com.acc.escola.model;

import com.acc.escola.enums.Sexo;
import com.acc.escola.enums.Tipo;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Setter
    @Column(name = "pessoa_sexo", nullable = false)
    private String sexo;

    @Setter
    @Column(name = "pessoa_tipo", nullable = false)
    private String tipo;


    public void setSexo(String sexo) {
        this.sexo = Sexo.toEnum(sexo).getCod();
    }

    public void setTipo(String tipo) {
        this.tipo = Tipo.toEnum(tipo).getCod();
    }

}
