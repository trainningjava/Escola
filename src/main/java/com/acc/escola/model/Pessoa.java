package com.acc.escola.model;

import com.acc.escola.enums.Sexo;
import com.acc.escola.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "pessoa_sexo", nullable = false)
    private Sexo sexo;

    @Enumerated(EnumType.STRING)
    @Column(name = "pessoa_tipo", nullable = false)
    private Tipo tipo;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, String cpf, Sexo sexo, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Enumerated(EnumType.STRING)
    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = Sexo.toEnum(sexo);
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = Tipo.toEnum(tipo);
    }

}
