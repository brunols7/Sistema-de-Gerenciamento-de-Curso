package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Certificado;
import com.curso.gerenciamento.domain.Matricula;
import com.curso.gerenciamento.domain.MatriculaId;
import com.curso.gerenciamento.service.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
@RequiredArgsConstructor
@Tag(name = "Matrículas", description = "Operações relacionadas às matrículas de alunos em cursos")
public class MatriculaController {
    private final MatriculaService matriculaService;

    @GetMapping
    @Operation(summary = "Listar todas as matrículas", description = "Retorna uma lista com todas as matrículas cadastradas no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de matrículas retornada com sucesso")
    })
    public List<Matricula> listar() {
        return matriculaService.listarTodas();
    }

    @GetMapping("/{alunoId}/{cursoId}")
    @Operation(summary = "Buscar matrícula por IDs", description = "Retorna uma matrícula específica pelos IDs do aluno e do curso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Matrícula encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public Matricula buscar(
            @Parameter(description = "ID do aluno") @PathVariable Long alunoId,
            @Parameter(description = "ID do curso") @PathVariable Long cursoId) {
        return matriculaService.buscarPorId(new MatriculaId(alunoId, cursoId));
    }

    @PostMapping
    @Operation(summary = "Criar nova matrícula", description = "Matricula um aluno em um curso")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Matrícula criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Matricula criar(@RequestBody Matricula matricula) {
        return matriculaService.salvar(matricula);
    }

    @DeleteMapping("/{alunoId}/{cursoId}")
    @Operation(summary = "Deletar matrícula", description = "Remove uma matrícula do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Matrícula deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public void deletar(
            @Parameter(description = "ID do aluno") @PathVariable Long alunoId,
            @Parameter(description = "ID do curso") @PathVariable Long cursoId) {
        matriculaService.deletar(new MatriculaId(alunoId, cursoId));
    }

    @GetMapping("/aluno/{id}")
    @Operation(summary = "Listar matrículas por aluno", description = "Retorna todas as matrículas de um aluno específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de matrículas retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public List<Matricula> listarPorAluno(@Parameter(description = "ID do aluno") @PathVariable Long id) {
        return matriculaService.listarPorAluno(id);
    }

    @PostMapping("/concluir")
    @Operation(summary = "Concluir curso e emitir certificado", description = "Marca um curso como concluído e emite o certificado para o aluno")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Curso concluído e certificado emitido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada"),
        @ApiResponse(responseCode = "400", description = "Erro ao concluir curso")
    })
    public Certificado concluir(@RequestBody MatriculaId id) {
        return matriculaService.concluirCurso(id);
    }

}
