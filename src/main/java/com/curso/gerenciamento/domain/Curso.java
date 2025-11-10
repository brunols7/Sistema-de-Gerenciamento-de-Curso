package com.curso.gerenciamento.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Integer cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToMany
    @JoinTable(
            name = "curso_categoria",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "curso")
    private List<Aula> aulas = new ArrayList<>();

    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas = new ArrayList<>();

    @OneToMany(mappedBy = "curso")
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}
