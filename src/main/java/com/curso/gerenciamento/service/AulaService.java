package com.curso.gerenciamento.service;

import com.curso.gerenciamento.domain.Aula;
import com.curso.gerenciamento.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {
    private final AulaRepository aulaRepository;

    public List<Aula> listarTodas() {
        return aulaRepository.findAll();
    }

    public Aula buscarPorId(Long id) {
        return aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
    }

    public Aula salvar(Aula aula) {
        return aulaRepository.save(aula);
    }

    public void deletar(Long id) {
        aulaRepository.deleteById(id);
    }

}
