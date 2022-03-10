package com.acc.escola.mapper;

import com.acc.escola.enums.Sexo;
import com.acc.escola.enums.Tipo;
import com.acc.escola.model.Aluno;
import com.acc.escola.model.Pessoa;
import com.acc.escola.model.responses.AlunoRespDTO;
import com.acc.escola.model.responses.PessoaRespDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {

    public List<PessoaRespDTO> convertListaPessoa (List<Pessoa> lista) {
        return lista.stream().map(resp -> {
            PessoaRespDTO dto = new PessoaRespDTO();
            dto.setId(resp.getId());
            dto.setCpf(resp.getCpf());
            dto.setNome(resp.getNome());
            dto.setSexo(Sexo.toDesc(resp.getSexo()));
            dto.setTipo(Tipo.toDesc(resp.getTipo()).replace("_", " "));
            return dto;
        }).collect(Collectors.toList());
    }
}
