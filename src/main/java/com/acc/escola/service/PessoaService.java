package com.acc.escola.service;

import com.acc.escola.model.Pessoa;
import com.acc.escola.repository.PessoaRepository;
import com.acc.escola.validate.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRep;

    public List<Pessoa> listAll() { return pessoaRep.findAll(); }

    public void save(Pessoa pessoa) throws Exception {

        if(Validate.isCPF(pessoa.getCpf())) {
          if(pessoa.getSexo()!= null)
            pessoaRep.save(pessoa);
          else{
              throw new Exception("Sexo inválido!");
          }
        }
        else {
            throw new Exception("CPF inválido!");

        }
    }

    public Optional<Pessoa> get(long id) {
        return pessoaRep.findById(id);
    }

    public void delete(long id) {
        pessoaRep.deleteById(id);
    }



// colocar a parte do enum
    // validar o cpf

}
