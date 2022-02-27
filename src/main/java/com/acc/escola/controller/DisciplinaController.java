package com.acc.escola.controller;

import com.acc.escola.model.Disciplina;
import com.acc.escola.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaSvc;

    @GetMapping
    public String viewDiscHome(Model model, RedirectAttributes redirectAttr) {
        List<Disciplina> lista =  disciplinaSvc.listAll();
        model.addAttribute("disciplinas", lista);
        return "disciplinas/index";
    }

    @GetMapping("new")
    public String newDisc(Model model) {
        Disciplina disciplina = new Disciplina();
        model.addAttribute("disciplina", disciplina);
        return "disciplinas/new";
    }

    @PostMapping("save")
    public String saveDisc(@Valid Disciplina disciplina, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "disciplinas/new";
        }
        disciplinaSvc.save(disciplina);
        return "redirect:/disciplinas";
    }

    @PostMapping("savedit")
    public String saveditDisc(@Valid Disciplina disciplina, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "disciplinas/edit";
        }
        disciplinaSvc.save(disciplina);
        return "redirect:/disciplinas";
    }

    @RequestMapping("edit/{id}")
    public ModelAndView editDisc(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        ModelAndView model = new ModelAndView("disciplinas/edit");
        Optional<Disciplina> disciplina = disciplinaSvc.get(id);
        if (!disciplina.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Disciplina " + id + " não foi encontrado");
            return new ModelAndView("redirect:/disciplinas");
        }
        model.addObject("disciplina", disciplina);
        return model;
    }

    @RequestMapping("delete/{id}")
    public String deleteDisc(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        Optional<Disciplina> disciplina = disciplinaSvc.get(id);
        if (!disciplina.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Disciplina " + id + " não foi encontrado");
        } else {
            disciplinaSvc.delete(id);
        }
        return "redirect:/disciplinas";
    }

}
