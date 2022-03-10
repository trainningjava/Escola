package com.acc.escola.service;

import com.acc.escola.model.Aluno;

import com.acc.escola.model.Disciplina;
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

    @Autowired
    private DisciplinaService disciplinaSvc;

    public List<Aluno> listAll() { return alunoRepository.findAll(); }

    public void save(Aluno aluno) {
        alunoRepository.save(aluno);
    }

    public Optional<Aluno> getAluno(long id) {
        return alunoRepository.findById(id);
    }

    public void delete(long id) {
        alunoRepository.deleteById(id);
    }

    public List<Aluno> getPessoa(long id) {
        return alunoRepository.findByPessoa (id);
    }

    public void valida(Aluno aluno) throws Exception {

        if( alunoRepository.countDisciplinasa(aluno.getTurma().getId()) >= 5) {
            throw new Exception("Quantidade de disciplina ultrapassada");
        }

        Integer valor = alunoRepository.sumCreditoPessoa(aluno.getPessoa().getId());
        if (valor == null) return;

        Optional<Disciplina> dis = disciplinaSvc.get(aluno.getDisciplina().getId());

        if(valor + dis.get().getCredito() > 15) {
            throw new Exception("Quantidade de créditos ultrapassada");
        }
    }

    public void calculaMensalidade(Aluno aluno) {
        if (aluno.getBolsa() == 0) {
            aluno.setMensalidade(1000d);
        } else {
            aluno.setMensalidade(1000 * ((100 -  aluno.getBolsa().doubleValue()) / 100));
        }
    }
}

