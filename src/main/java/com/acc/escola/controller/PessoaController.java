package com.acc.escola.controller;

import java.util.List;
import java.util.Optional;


import com.acc.escola.model.Pessoa;
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
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaSvc;

    @GetMapping
    public String viewPessoaHome(Model model, RedirectAttributes redirectAttr) {
        List<Pessoa> lista =  pessoaSvc.listAll();
        model.addAttribute("pessoa", lista);
        return "pessoa/index";
    }
    @GetMapping("new")
    public String newPessoa(Model model) {
        Pessoa pessoa = new Pessoa();
        model.addAttribute("pessoa", pessoa);
        return "pessoa/new";
    }
    @PostMapping("save")
    public String savePessoa(@Valid Pessoa pessoa, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "pessoa/new";
        }
        try {
            pessoaSvc.save(pessoa);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.getMessage());

        }

        return "redirect:/pessoa";
    }
    @PostMapping("savedit")
    public String saveditPessoa(@Valid Pessoa pessoa, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "pessoa/edit";
        }
        try {
            pessoaSvc.save(pessoa);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/pessoa";
    }
    @RequestMapping("edit/{id}")
    public ModelAndView editPessoa(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        ModelAndView model = new ModelAndView("pessoa/edit");
        Optional<Pessoa> pessoa = pessoaSvc.get(id);
        if (!pessoa.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Pessoa " + id + " não foi encontrado");
            return new ModelAndView("redirect:/pessoa");
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
        return "redirect:/pessoa";
    }
}
