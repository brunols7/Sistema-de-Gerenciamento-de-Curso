package com.curso.gerenciamento.repository;

import com.curso.gerenciamento.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
