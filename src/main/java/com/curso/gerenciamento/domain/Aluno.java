package com.curso.gerenciamento.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas = new ArrayList<>();

    @OneToMany(mappedBy = "aluno")
    private List<Avaliacao> avaliacoes = new ArrayList<>();

}
