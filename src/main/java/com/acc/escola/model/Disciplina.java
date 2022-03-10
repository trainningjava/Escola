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
public class Disciplina implements Serializable {

    private static final long serialVersionUID = -5965960920030714892L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser em branco")
    @Column(length = 45)
    private String nome;

    private Integer credito;
}
