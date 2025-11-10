package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Curso;
import com.curso.gerenciamento.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return cursoService.salvar(curso);
    }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.salvar(curso);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        cursoService.deletar(id);
    }

    @GetMapping("/professor/{id}")
    public List<Curso> buscarPorProfessor(@PathVariable Long id) {
        return cursoService.buscarPorProfessor(id);
    }

    @GetMapping("/buscar")
    public List<Curso> buscarPorTitulo(@RequestParam String titulo) {
        return cursoService.buscarPorTitulo(titulo);
    }

}
