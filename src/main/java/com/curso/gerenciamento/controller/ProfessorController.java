package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Professor;
import com.curso.gerenciamento.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping
    public List<Professor> listar() {
        return professorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Professor buscar(@PathVariable Long id) {
        return professorService.buscarPorId(id);
    }

    @PostMapping
    public Professor criar(@RequestBody Professor professor) {
        return professorService.salvar(professor);
    }

    @PutMapping("/{id}")
    public Professor atualizar(@PathVariable Long id, @RequestBody Professor professor) {
        professor.setId(id);
        return professorService.salvar(professor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        professorService.deletar(id);
    }
}
