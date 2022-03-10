package com.acc.escola.repository;

import com.acc.escola.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

//    List<Pessoa> findByAlunoId(Long alunoID);
}