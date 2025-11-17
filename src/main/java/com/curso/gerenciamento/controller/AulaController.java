package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Aula;
import com.curso.gerenciamento.service.AulaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aulas")
@RequiredArgsConstructor
@Tag(name = "Aulas", description = "Operações relacionadas às aulas")
public class AulaController {

    private final AulaService aulaService;

    @GetMapping
    @Operation(summary = "Listar todas as aulas", description = "Retorna uma lista com todas as aulas cadastradas no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de aulas retornada com sucesso")
    })
    public List<Aula> listar() {
        return aulaService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aula por ID", description = "Retorna uma aula específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aula encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    public Aula buscar(@Parameter(description = "ID da aula") @PathVariable Long id) {
        return aulaService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar nova aula", description = "Cadastra uma nova aula no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aula criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Aula criar(@RequestBody Aula aula) {
        return aulaService.salvar(aula);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aula", description = "Atualiza os dados de uma aula existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aula atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aula não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Aula atualizar(@Parameter(description = "ID da aula") @PathVariable Long id, @RequestBody Aula aula) {
        aula.setId(id);
        return aulaService.salvar(aula);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar aula", description = "Remove uma aula do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Aula deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aula não encontrada")
    })
    public void deletar(@Parameter(description = "ID da aula") @PathVariable Long id) {
        aulaService.deletar(id);
    }

}
