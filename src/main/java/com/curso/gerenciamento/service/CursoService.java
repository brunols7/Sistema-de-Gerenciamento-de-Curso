package com.curso.gerenciamento.service;

import com.curso.gerenciamento.domain.Curso;
import com.curso.gerenciamento.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> buscarPorProfessor(Long professorId) {
        return cursoRepository.findByProfessorId(professorId);
    }

    public List<Curso> buscarPorTitulo(String termo) {
        return cursoRepository.findByTituloContainingIgnoreCase(termo);
    }

}
