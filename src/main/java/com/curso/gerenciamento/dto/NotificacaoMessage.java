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
public class NotificacaoMessage implements Serializable {
    private String id;
    private String destinatario;
    private String titulo;
    private String conteudo;
    private LocalDateTime dataEnvio;
}
