package com.acc.escola.mapper;

import com.acc.escola.enums.Bolsa;
import com.acc.escola.model.Aluno;
import com.acc.escola.model.responses.AlunoRespDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlunoMapper {

    public List<AlunoRespDTO> convertListaAluno (List<Aluno> lista){
        return lista.stream().map(resp -> {
            AlunoRespDTO dto = new AlunoRespDTO();
            dto.setId(resp.getId());
            dto.setPessoa(resp.getPessoa().getNome());
            dto.setTurma(resp.getTurma().getNome());
            dto.setDisciplina(resp.getDisciplina().getNome());
            dto.setBolsa(Bolsa.toDesc(resp.getBolsa()));
            dto.setMensalidade(resp.getMensalidade());
            return dto;
        }).collect(Collectors.toList());
    }

}
