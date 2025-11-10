package com.curso.gerenciamento.repository;

import com.curso.gerenciamento.domain.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
}
