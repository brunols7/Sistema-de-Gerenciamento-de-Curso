package com.curso.gerenciamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEventMessage implements Serializable {
    private String eventId;
    private String tipoEvento;
    private Long cursoId;
    private String cursoTitulo;
    private LocalDateTime timestamp;
}
