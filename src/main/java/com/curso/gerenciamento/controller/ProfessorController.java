package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Professor;
import com.curso.gerenciamento.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/professores")
@RequiredArgsConstructor
@Tag(name = "Professores", description = "Operações relacionadas aos professores")
public class ProfessorController {
    private final ProfessorService professorService;

    @GetMapping
    @Operation(summary = "Listar todos os professores", description = "Retorna uma lista com todos os professores cadastrados no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de professores retornada com sucesso")
    })
    public List<Professor> listar() {
        return professorService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar professor por ID", description = "Retorna um professor específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public Professor buscar(@Parameter(description = "ID do professor") @PathVariable Long id) {
        return professorService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo professor", description = "Cadastra um novo professor no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Professor criar(@RequestBody Professor professor) {
        return professorService.salvar(professor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar professor", description = "Atualiza os dados de um professor existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Professor atualizar(@Parameter(description = "ID do professor") @PathVariable Long id, @RequestBody Professor professor) {
        professor.setId(id);
        return professorService.salvar(professor);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar professor", description = "Remove um professor do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public void deletar(@Parameter(description = "ID do professor") @PathVariable Long id) {
        professorService.deletar(id);
    }
}
