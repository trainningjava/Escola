package com.acc.escola.model;


import com.acc.escola.enums.AlunoTipoBolsa;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class Aluno extends Pessoa {

    private static final long serialVersionUID = 7670534790770756526L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotEmpty(message = "Este campo nome não deve ser vazio")
    private int alunoVLMensalidade ;

    private AlunoTipoBolsa alunoTipoBolsa;

    public Aluno() {
    }

    public Aluno(int alunoVLMensalidade, AlunoTipoBolsa code ) {
        this.id = id;
        this.alunoTipoBolsa = code;
        this.alunoVLMensalidade = alunoVLMensalidade;

    }

    public void calculaMensalidade (float alunoVLMensalidade, AlunoTipoBolsa code ){
        this.alunoVLMensalidade = (int) (alunoVLMensalidade * code.getCode());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAlunoVLMensalidade() {
        return alunoVLMensalidade;
    }

    public void setAlunoVLMensalidade(float alunoVLMensalidade) {
        this.alunoVLMensalidade = (int) alunoVLMensalidade;
    }

    public AlunoTipoBolsa getAlunoTipoBolsa() {
        return alunoTipoBolsa;
    }

    public void setAlunoTipoBolsa(AlunoTipoBolsa alunoTipoBolsa) {
        this.alunoTipoBolsa = alunoTipoBolsa;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", alunoVLMensalidade=" + alunoVLMensalidade +
                ", alunoTipoBolsa=" + alunoTipoBolsa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Float.compare(aluno.alunoVLMensalidade, alunoVLMensalidade) == 0 && id.equals(aluno.id) && alunoTipoBolsa == aluno.alunoTipoBolsa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alunoVLMensalidade, alunoTipoBolsa);
    }
}
