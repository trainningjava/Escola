package com.acc.escola.service;

import com.acc.escola.model.Turma;
import com.acc.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRep;

    public List<Turma> listAll() {
        return turmaRep.findAll();
    }

    public void save(Turma turma) {
        turmaRep.save(turma);
    }

    public Optional<Turma> get(long id) {
        return turmaRep.findById(id);
    }

    public void delete(long id) {
        turmaRep.deleteById(id);
    }

}
