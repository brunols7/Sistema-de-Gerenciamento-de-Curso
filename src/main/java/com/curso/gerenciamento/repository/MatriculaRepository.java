package com.curso.gerenciamento.repository;

import com.curso.gerenciamento.domain.Matricula;
import com.curso.gerenciamento.domain.MatriculaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, MatriculaId> {

    List<Matricula> findByAlunoId(Long alunoId);

    List<Matricula> findByCursoId(Long cursoId);
}
