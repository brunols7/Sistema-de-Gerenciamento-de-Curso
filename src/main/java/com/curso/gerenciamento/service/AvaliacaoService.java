package com.curso.gerenciamento.service;

import com.curso.gerenciamento.domain.Avaliacao;
import com.curso.gerenciamento.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listarTodas() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao buscarPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public void deletar(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public Double calcularMediaPorCurso(Long cursoId) {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByCursoId(cursoId);
        if (avaliacoes.isEmpty()) return 0.0;

        return avaliacoes.stream()
                .mapToDouble(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }

}
