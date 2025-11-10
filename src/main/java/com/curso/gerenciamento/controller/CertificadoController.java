package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.domain.Certificado;
import com.curso.gerenciamento.service.CertificadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/certificados")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoService certificadoService;

    @GetMapping
    public List<Certificado> listar() {
        return certificadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Certificado buscar(@PathVariable Long id) {
        return certificadoService.buscarPorId(id);
    }

    @PostMapping
    public Certificado criar(@RequestBody Certificado certificado) {
        return certificadoService.salvar(certificado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        certificadoService.deletar(id);
    }

}
