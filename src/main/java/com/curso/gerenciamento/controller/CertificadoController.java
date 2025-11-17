package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Certificado;
import com.curso.gerenciamento.service.CertificadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/certificados")
@RequiredArgsConstructor
@Tag(name = "Certificados", description = "Operações relacionadas aos certificados")
public class CertificadoController {

    private final CertificadoService certificadoService;

    @GetMapping
    @Operation(summary = "Listar todos os certificados", description = "Retorna uma lista com todos os certificados emitidos no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de certificados retornada com sucesso")
    })
    public List<Certificado> listar() {
        return certificadoService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar certificado por ID", description = "Retorna um certificado específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Certificado encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Certificado não encontrado")
    })
    public Certificado buscar(@Parameter(description = "ID do certificado") @PathVariable Long id) {
        return certificadoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar novo certificado", description = "Emite um novo certificado no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Certificado criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Certificado criar(@RequestBody Certificado certificado) {
        return certificadoService.salvar(certificado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar certificado", description = "Remove um certificado do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Certificado deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Certificado não encontrado")
    })
    public void deletar(@Parameter(description = "ID do certificado") @PathVariable Long id) {
        certificadoService.deletar(id);
    }

}
