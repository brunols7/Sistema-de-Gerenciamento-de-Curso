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
public class Certificado {

    @EmbeddedId
    private MatriculaId id;

    private LocalDate dataEmissao;
    private String codigo;

    @OneToOne
    @MapsId
    @JoinColumns({
            @JoinColumn(name = "aluno_id", referencedColumnName = "aluno_id"),
            @JoinColumn(name = "curso_id", referencedColumnName = "curso_id")
    })
    private Matricula matricula;

}
