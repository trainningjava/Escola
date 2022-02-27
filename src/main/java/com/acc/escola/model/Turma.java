package com.acc.escola.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turma implements Serializable {

    private static final long serialVersionUID = 4469597225621257119L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser em branco")
    @Column(length = 45)
    private String nome;
}
