package com.curso.gerenciamento.service;

import com.curso.gerenciamento.domain.Professor;
import com.curso.gerenciamento.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deletar(Long id) {
        professorRepository.deleteById(id);
    }

}
