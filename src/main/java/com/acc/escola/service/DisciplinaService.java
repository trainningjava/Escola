package com.acc.escola.service;

import com.acc.escola.model.Disciplina;
import com.acc.escola.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disRep;

    public List<Disciplina> listAll() { return disRep.findAll(); }

    public void save(Disciplina disciplina) {
        disRep.save(disciplina);
    }

    public Optional<Disciplina> get(long id) {
        return disRep.findById(id);
    }

    public void delete(long id) {
        disRep.deleteById(id);
    }

}
