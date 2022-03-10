package com.acc.escola.controller;

import com.acc.escola.enums.Bolsa;
import com.acc.escola.enums.Sexo;
import com.acc.escola.enums.Tipo;
import com.acc.escola.model.Aluno;
import com.acc.escola.model.responses.AlunoRespDTO;
import com.acc.escola.model.responses.PessoaRespDTO;
import com.acc.escola.repository.PessoaRepository;
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

    @GetMapping
    public String listarAlunos(Model model, RedirectAttributes redirectAttr) {
        List<Aluno> lista =  alunoService.listAll();
        List<AlunoRespDTO> listaResp = lista.stream().map(resp -> {
            AlunoRespDTO dto = new AlunoRespDTO();
            dto.setId(resp.getId());
            dto.setPessoa(resp.getPessoa().getNome());
            dto.setTurma(resp.getTurma().getNome());
            dto.setDisciplina(resp.getDisciplina().getNome());
            dto.setBolsa(Bolsa.toDesc(resp.getBolsa()));
            dto.setMensalidade(resp.getMensalidade());
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("alunos", listaResp);
        return "alunos/index";
    }

    @GetMapping("new")
    public String cadastrarAluno(Model model) {
        Aluno aluno = new Aluno();
        model.addAttribute("aluno", aluno);
        model.addAttribute("listaAlunos", pessoaSvc.listAll());
        model.addAttribute("listaTurmas", turmaSrv.listAll());
        model.addAttribute("listaDisciplinas", disciplinaSvc.listAll());
        model.addAttribute("listaBolsas", Bolsa.values());
        return "alunos/new";
    }

    @PostMapping("save")
    public String saveAluno(@Valid Aluno aluno, BindingResult bindingResult, RedirectAttributes redirectAttr) throws Exception {
        if (bindingResult.hasErrors()) {
            redirectAttr.addFlashAttribute("errorMessage", "Erro ao salvar");
            return "alunos/new";
        }
        try {
            if (aluno.getBolsa() == 0) {
                aluno.setMensalidade(1000d);
            } else {
                aluno.setMensalidade(1000 * ((100 -  aluno.getBolsa().doubleValue()) / 100));
            }

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
            if (aluno.getBolsa() == 0) {
                aluno.setMensalidade(1000d);
            } else {
                aluno.setMensalidade(1000 * ((100 -  aluno.getBolsa().doubleValue()) / 100));
            }
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
        model.addObject("listaAlunos", pessoaSvc.listAll());
        model.addObject("listaTurmas", turmaSrv.listAll());
        model.addObject("listaDisciplinas", disciplinaSvc.listAll());
        model.addObject("listaBolsas", Bolsa.values());
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
