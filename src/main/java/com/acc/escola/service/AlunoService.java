package com.acc.escola.service;

import com.acc.escola.model.Aluno;

import com.acc.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listAll() { return alunoRepository.findAll(); }

    public void save(Aluno aluno) throws Exception {
        alunoRepository.save(aluno);

    }
    public Optional<Aluno> getAluno(long id) {
        return alunoRepository.findById(id);
    }

    public void delete(long id) {
        alunoRepository.deleteById(id);
    }

}

