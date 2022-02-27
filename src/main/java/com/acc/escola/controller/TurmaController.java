package com.acc.escola.controller;

import java.util.List;
import java.util.Optional;

import com.acc.escola.model.Turma;
import com.acc.escola.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaSrv;

    @GetMapping
    public String viewTurmaHome(Model model, RedirectAttributes redirectAttr) {
        List<Turma> lista =  turmaSrv.listAll();
        model.addAttribute("turmas", lista);
        return "turmas/index";
    }

    @GetMapping("new")
    public String newTurma(Model model) {
        Turma turma = new Turma();
        model.addAttribute("turma", turma);
        return "turmas/new";
    }

    @PostMapping("save")
    public String saveTurma(@Valid Turma turma, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "turmas/new";
        }
        turmaSrv.save(turma);
        return "redirect:/turmas";
    }

    @PostMapping("savedit")
    public String saveditTurma(@Valid Turma turma, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "turmas/edit";
        }
        turmaSrv.save(turma);
        return "redirect:/turmas";
    }

    @RequestMapping("edit/{id}")
    public ModelAndView editTurma(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        ModelAndView mv = new ModelAndView("turmas/edit");
        Optional<Turma> turma = turmaSrv.get(id);
        if (!turma.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Turma " + id + " não foi encontrado");
            return new ModelAndView( "redirect:/turmas");
        }
        mv.addObject("turma", turma);
        return mv;
    }

    @RequestMapping("delete/{id}")
    public String deleteTurma(@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        Optional<Turma> turma = turmaSrv.get(id);
        if (!turma.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Turma " + id + " não foi encontrado");
        } else {
            turmaSrv.delete(id);
        }
        return "redirect:/turmas";
    }

}
