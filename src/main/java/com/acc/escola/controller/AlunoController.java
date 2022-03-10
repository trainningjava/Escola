package com.acc.escola.controller;

import com.acc.escola.enums.Bolsa;
import com.acc.escola.enums.Tipo;
import com.acc.escola.mapper.AlunoMapper;
import com.acc.escola.model.Aluno;
import com.acc.escola.model.Disciplina;
import com.acc.escola.model.Pessoa;
import com.acc.escola.model.responses.AlunoRespDTO;
import com.acc.escola.service.AlunoService;
import com.acc.escola.service.DisciplinaService;
import com.acc.escola.service.PessoaService;
import com.acc.escola.service.TurmaService;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private PessoaService pessoaSvc;

    @Autowired
    private TurmaService turmaSrv;

    @Autowired
    private DisciplinaService disciplinaSvc;

    @Autowired
    AlunoMapper alunoMapper;

    @GetMapping
    public String listarAlunos(Model model, RedirectAttributes redirectAttr) {
        List<Aluno> lista =  alunoService.listAll();
        model.addAttribute("alunos", alunoMapper.convertListaAluno(lista));
        return "alunos/index";
    }

    @GetMapping("new")
    public String cadastrarAluno(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        model.addAttribute("listaAlunos", pessoaSvc.listAll());
        return "alunos/new";
    }

    @PostMapping("new1")
    public ModelAndView cadastrarAluno1(@Valid Aluno aluno, BindingResult bindingResult, RedirectAttributes redirectAttr) {

        ModelAndView model = new ModelAndView("alunos/newfinal");

        Optional<Pessoa> pessoa = pessoaSvc.get(aluno.getPessoa().getId());
        if (!pessoa.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Aluno " + aluno.getPessoa().getId() + " não cadastrado");
            return new ModelAndView("redirect:/alunos");
        }
        aluno.getPessoa().setNome(pessoa.get().getNome());
        model.addObject("aluno", aluno);
        model.addObject("listaTurmas", turmaSrv.listAll());
        model.addObject("listaDisciplinas", disciplinaSvc.listAll());
        if (pessoa.get().getTipo().equals(Tipo.SEM_BOLSA.getCod())) {
            model.addObject("listaBolsas", Bolsa.B0);
        } else {
            model.addObject("listaBolsas", Bolsa.values());
        }
        return model;
    }

    @PostMapping("save")
    public String saveAluno(@Valid Aluno aluno, BindingResult bindingResult, RedirectAttributes redirectAttr) throws Exception {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "redirect:/alunos/new";
        }
        try {
            alunoService.calculaMensalidade(aluno);

            alunoService.valida(aluno);

            alunoService.save(aluno);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.toString());
            return "redirect:/alunos/new";
        }
        return "redirect:/alunos";
    }

    @PostMapping("savedit")
    public String savEditPessoa(@Valid Aluno aluno, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "redirect:/alunos/edit";
        }
        try {
            alunoService.calculaMensalidade(aluno);

            alunoService.valida(aluno);

            alunoService.save(aluno);

        } catch (Exception e){
            redirectAttr.addFlashAttribute("errorMessage", e.getMessage());

        }
        return "redirect:/alunos";
    }

    @RequestMapping("edit/{id}")
    public ModelAndView editaDadosAluno (@PathVariable(name = "id") int id, RedirectAttributes redirectAttr) {
        ModelAndView model = new ModelAndView("alunos/edit");
        Optional<Aluno> aluno = alunoService.getAluno(id);
        if (!aluno.isPresent()) {
            redirectAttr.addFlashAttribute("errorMessage", "Aluno " + id + " não cadastrado");
            return new ModelAndView("redirect:/alunos");
        }
        model.addObject("aluno", aluno.get());
        model.addObject("listaTurmas", turmaSrv.listAll());
        model.addObject("listaDisciplinas", disciplinaSvc.listAll());
        if (aluno.get().getPessoa().getTipo().equals(Tipo.SEM_BOLSA.getCod())) {
            model.addObject("listaBolsas", Bolsa.B0);
        } else {
            model.addObject("listaBolsas", Bolsa.values());
        }
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
