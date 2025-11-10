package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Aula;
import com.curso.gerenciamento.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
public class AulaController {

    private final AulaService aulaService;

    @GetMapping
    public List<Aula> listar() {
        return aulaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Aula buscar(@PathVariable Long id) {
        return aulaService.buscarPorId(id);
    }

    @PostMapping
    public Aula criar(@RequestBody Aula aula) {
        return aulaService.salvar(aula);
    }

    @PutMapping("/{id}")
    public Aula atualizar(@PathVariable Long id, @RequestBody Aula aula) {
        aula.setId(id);
        return aulaService.salvar(aula);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        aulaService.deletar(id);
    }

}
