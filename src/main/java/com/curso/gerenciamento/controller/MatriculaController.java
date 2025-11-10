package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Certificado;
import com.curso.gerenciamento.domain.Matricula;
import com.curso.gerenciamento.domain.MatriculaId;
import com.curso.gerenciamento.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {
    private final MatriculaService matriculaService;

    @GetMapping
    public List<Matricula> listar() {
        return matriculaService.listarTodas();
    }

    @GetMapping("/{alunoId}/{cursoId}")
    public Matricula buscar(@PathVariable Long alunoId, @PathVariable Long cursoId) {
        return matriculaService.buscarPorId(new MatriculaId(alunoId, cursoId));
    }

    @PostMapping
    public Matricula criar(@RequestBody Matricula matricula) {
        return matriculaService.salvar(matricula);
    }

    @DeleteMapping("/{alunoId}/{cursoId}")
    public void deletar(@PathVariable Long alunoId, @PathVariable Long cursoId) {
        matriculaService.deletar(new MatriculaId(alunoId, cursoId));
    }

    @GetMapping("/aluno/{id}")
    public List<Matricula> listarPorAluno(@PathVariable Long id) {
        return matriculaService.listarPorAluno(id);
    }

    @PostMapping("/concluir")
    public Certificado concluir(@RequestBody MatriculaId id) {
        return matriculaService.concluirCurso(id);
    }

}
