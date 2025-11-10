package com.curso.gerenciamento.repository;

import com.curso.gerenciamento.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByTituloContainingIgnoreCase(String titulo);

    List<Curso> findByProfessorId(Long professorId);
}
