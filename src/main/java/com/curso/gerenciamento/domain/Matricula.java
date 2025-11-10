package com.curso.gerenciamento.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @EmbeddedId
    private MatriculaId id;

    @ManyToOne
    @MapsId("alunoId")
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @MapsId("cursoId")
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private LocalDate dataMatricula;
    private boolean concluido;

    @OneToOne(mappedBy = "matricula", cascade = CascadeType.ALL)
    private Certificado certificado;

}
