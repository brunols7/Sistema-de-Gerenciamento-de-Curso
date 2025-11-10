package com.curso.gerenciamento.repository;

import com.curso.gerenciamento.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
