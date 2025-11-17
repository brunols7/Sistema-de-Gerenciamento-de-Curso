package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Aluno;
import com.curso.gerenciamento.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Tag(name = "Alunos", description = "Operações relacionadas aos alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista com todos os alunos cadastrados no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
    })
    public List<Aluno> listar() {
        return alunoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID", description = "Retorna um aluno específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public Aluno buscar(@Parameter(description = "ID do aluno") @PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo aluno", description = "Cadastra um novo aluno no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Aluno atualizar(@Parameter(description = "ID do aluno") @PathVariable Long id, @RequestBody Aluno aluno) {
        aluno.setId(id);
        return alunoService.salvar(aluno);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aluno", description = "Remove um aluno do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aluno deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public void deletar(@Parameter(description = "ID do aluno") @PathVariable Long id) {
        alunoService.deletar(id);
    }

}
