package com.acc.escola.controller;

import com.acc.escola.enums.AlunoTipoBolsa;
import com.acc.escola.model.Aluno;
import com.acc.escola.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String listarAlunos(Model model, RedirectAttributes redirectAttr) {
        List<Aluno> lista =  alunoService.listAll();
        model.addAttribute("Alunos: ", lista);
        return "alunos/index";
    }
    @GetMapping("new")
    public String cadastrarAluno(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        model.addAttribute("alunoTipoBolsa", AlunoTipoBolsa.values());

        return "alunos/new";
    }
    @PostMapping("save")
    public String saveAluno(@Valid Aluno aluno, BindingResult bindingResult, RedirectAttributes redirectAttr) throws Exception {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "alunos/new";
        }
        try {
            alunoService.save(aluno);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/alunos";
    }
    @PostMapping("savedit")
    public String savEditPessoa(@Valid Aluno aluno, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "alunos/edit";
        }
        try {
            alunoService.save(aluno);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/alunos";
    }
    @RequestMapping("edit/{id}")
    public ModelAndView editaDadosAluno (@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        ModelAndView model = new ModelAndView("aluno/edit");
        Optional<Aluno> aluno = alunoService.getAluno(id);
        if (!aluno.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Aluno " + id + " não cadastrado");
            return new ModelAndView("redirect:/alunos");
        }
        model.addObject("aluno", aluno);
        return model;
    }
    @RequestMapping("delete/{id}")
    public String deleteAluno(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        Optional<Aluno> aluno = alunoService.getAluno(id);
        if (!aluno.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Aluno " + id + " não cadastrado");
        } else {
            alunoService.delete(id);
        }
        return "redirect:/alunos";
    }
}
