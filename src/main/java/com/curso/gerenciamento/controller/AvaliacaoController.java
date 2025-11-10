package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Avaliacao;
import com.curso.gerenciamento.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> listar() {
        return avaliacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public Avaliacao buscar(@PathVariable Long id) {
        return avaliacaoService.buscarPorId(id);
    }

    @PostMapping
    public Avaliacao criar(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.salvar(avaliacao);
    }

    @PutMapping("/{id}")
    public Avaliacao atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        avaliacao.setId(id);
        return avaliacaoService.salvar(avaliacao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        avaliacaoService.deletar(id);
    }

    @GetMapping("/curso/{id}/media")
    public Double mediaPorCurso(@PathVariable Long id) {
        return avaliacaoService.calcularMediaPorCurso(id);
    }

}
