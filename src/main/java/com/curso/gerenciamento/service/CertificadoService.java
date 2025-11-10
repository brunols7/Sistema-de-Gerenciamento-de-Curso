package com.curso.gerenciamento.service;

import com.curso.gerenciamento.domain.Certificado;
import com.curso.gerenciamento.repository.CertificadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;

    public List<Certificado> listarTodos() {
        return certificadoRepository.findAll();
    }

    public Certificado buscarPorId(Long id) {
        return certificadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado não encontrado"));
    }

    public Certificado salvar(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    public void deletar(Long id) {
        certificadoRepository.deleteById(id);
    }

}
