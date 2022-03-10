package com.acc.escola.repository;

import com.acc.escola.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("from Aluno where pessoa.id = :id")
    List<Aluno> findByPessoa(Long id);
}