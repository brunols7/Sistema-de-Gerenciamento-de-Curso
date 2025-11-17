package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Curso;
import com.curso.gerenciamento.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
@Tag(name = "Cursos", description = "Operações relacionadas aos cursos")
public class CursoController {

    private final CursoService cursoService;

    @GetMapping
    @Operation(summary = "Listar todos os cursos", description = "Retorna uma lista com todos os cursos cadastrados no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso")
    })
    public List<Curso> listar() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Retorna um curso específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public Curso buscar(@Parameter(description = "ID do curso") @PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo curso", description = "Cadastra um novo curso no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Curso criar(@RequestBody Curso curso) {
        return cursoService.salvar(curso);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualiza os dados de um curso existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Curso atualizar(@Parameter(description = "ID do curso") @PathVariable Long id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.salvar(curso);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar curso", description = "Remove um curso do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public void deletar(@Parameter(description = "ID do curso") @PathVariable Long id) {
        cursoService.deletar(id);
    }

    @GetMapping("/professor/{id}")
    @Operation(summary = "Buscar cursos por professor", description = "Retorna todos os cursos ministrados por um professor específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado")
    })
    public List<Curso> buscarPorProfessor(@Parameter(description = "ID do professor") @PathVariable Long id) {
        return cursoService.buscarPorProfessor(id);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar cursos por título", description = "Retorna todos os cursos que contenham o título informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cursos retornada com sucesso")
    })
    public List<Curso> buscarPorTitulo(@Parameter(description = "Título do curso para busca") @RequestParam String titulo) {
        return cursoService.buscarPorTitulo(titulo);
    }

}
