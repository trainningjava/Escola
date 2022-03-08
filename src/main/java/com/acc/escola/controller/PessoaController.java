package com.acc.escola.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.acc.escola.enums.Sexo;
import com.acc.escola.enums.Tipo;
import com.acc.escola.model.Pessoa;
import com.acc.escola.model.responses.PessoaRespDTO;
import com.acc.escola.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaSvc;

    private static Pessoa apply(Pessoa mig) {
        mig.setSexo(Sexo.toEnum(mig.getSexo()).name());
        mig.setTipo(Sexo.toEnum(mig.getTipo()).name());
        return mig;
    }

    @GetMapping
    public String viewPessoaHome(Model model, RedirectAttributes redirectAttr) {
        List<Pessoa> lista =  pessoaSvc.listAll();
        List<PessoaRespDTO> listaResp = lista.stream().map(resp -> {
            PessoaRespDTO dto = new PessoaRespDTO();
            dto.setId(resp.getId());
            dto.setCpf(resp.getCpf());
            dto.setNome(resp.getNome());
            dto.setSexo(Sexo.toDesc(resp.getSexo()));
            dto.setTipo( Tipo.toDesc(resp.getTipo()).replace("_", " "));
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("pessoas", listaResp);
        return "pessoas/index";
    }
    @GetMapping("new")
    public String newPessoa(Model model) {
        Pessoa pessoa = new Pessoa();
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("listaSexos", Sexo.values());
        model.addAttribute("listaTipos", Tipo.values());
        return "pessoas/new";
    }
    @PostMapping("save")
    public String savePessoa(@Valid Pessoa pessoa, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "pessoas/new";
        }
        try {
            pessoaSvc.save(pessoa);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.getMessage());

        }

        return "redirect:/pessoas";
    }
    @PostMapping("savedit")
    public String saveditPessoa(@Valid Pessoa pessoa, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "pessoas/edit";
        }
        try {
            pessoaSvc.save(pessoa);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/pessoas";
    }
    @RequestMapping("edit/{id}")
    public ModelAndView editPessoa(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        ModelAndView model = new ModelAndView("pessoas/edit");
        Optional<Pessoa> pessoa = pessoaSvc.get(id);
        if (!pessoa.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Pessoa " + id + " não foi encontrado");
            return new ModelAndView("redirect:/pessoas");
        }
        model.addObject("pessoa", pessoa);
        return model;
    }
    @RequestMapping("delete/{id}")
    public String deletePessoa(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        Optional<Pessoa> pessoa = pessoaSvc.get(id);
        if (!pessoa.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Pessoa " + id + " não foi encontrado");
        } else {
            pessoaSvc.delete(id);
        }
        return "redirect:/pessoas";
    }
}
