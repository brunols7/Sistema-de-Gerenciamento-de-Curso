package com.curso.gerenciamento.repository;

import com.curso.gerenciamento.domain.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long> {
}