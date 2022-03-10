package com.acc.escola.repository;

import com.acc.escola.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("from Aluno where pessoa.id = :id")
    List<Aluno> findByPessoa(Long id);

    @Query("select count(*) from Aluno where turma.id = :id")
    Integer countDisciplinasa(Long id);

    @Query("Select sum(disciplina.credito) from Aluno where pessoa.id = :id")
    Integer sumCreditoPessoa(Long id);

}