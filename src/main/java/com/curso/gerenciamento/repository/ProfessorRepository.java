package com.curso.gerenciamento.repository;

import com.curso.gerenciamento.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
