package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Avaliacao;
import com.curso.gerenciamento.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
@Tag(name = "Avaliações", description = "Operações relacionadas às avaliações")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping
    @Operation(summary = "Listar todas as avaliações", description = "Retorna uma lista com todas as avaliações cadastradas no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso")
    })
    public List<Avaliacao> listar() {
        return avaliacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar avaliação por ID", description = "Retorna uma avaliação específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public Avaliacao buscar(@Parameter(description = "ID da avaliação") @PathVariable Long id) {
        return avaliacaoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar nova avaliação", description = "Cadastra uma nova avaliação no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Avaliacao criar(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.salvar(avaliacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar avaliação", description = "Atualiza os dados de uma avaliação existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Avaliacao atualizar(@Parameter(description = "ID da avaliação") @PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        avaliacao.setId(id);
        return avaliacaoService.salvar(avaliacao);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar avaliação", description = "Remove uma avaliação do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    })
    public void deletar(@Parameter(description = "ID da avaliação") @PathVariable Long id) {
        avaliacaoService.deletar(id);
    }

    @GetMapping("/curso/{id}/media")
    @Operation(summary = "Calcular média de avaliações por curso", description = "Retorna a média das avaliações de um curso específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Média calculada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public Double mediaPorCurso(@Parameter(description = "ID do curso") @PathVariable Long id) {
        return avaliacaoService.calcularMediaPorCurso(id);
    }

}
