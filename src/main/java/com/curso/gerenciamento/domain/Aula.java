package com.curso.gerenciamento.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String urlVideo;
    private Integer duracao;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
