package com.curso.gerenciamento.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor

public class MatriculaId implements Serializable {

    private Long alunoId;
    private Long cursoId;

}
