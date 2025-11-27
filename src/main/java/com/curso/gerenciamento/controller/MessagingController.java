package com.curso.gerenciamento.controller;

import com.curso.gerenciamento.dto.CursoEventMessage;
import com.curso.gerenciamento.dto.NotificacaoMessage;
import com.curso.gerenciamento.service.queue.NotificacaoProducer;
import com.curso.gerenciamento.service.topic.CursoEventPublisher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mensagens")
@RequiredArgsConstructor
@Tag(name = "Mensageria", description = "Operações de mensageria com ActiveMQ")
public class MessagingController {

    private final NotificacaoProducer notificacaoProducer;
    private final CursoEventPublisher cursoEventPublisher;

    @PostMapping("/notificacao")
    @Operation(summary = "Enviar notificação", description = "Envia uma notificação para a fila (Queue)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificação enviada com sucesso")
    })
    public ResponseEntity<NotificacaoMessage> enviarNotificacao(@RequestBody NotificacaoMessage message) {
        notificacaoProducer.enviarNotificacao(message);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/curso-evento")
    @Operation(summary = "Publicar evento de curso", description = "Publica um evento no topic de cursos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Evento publicado com sucesso")
    })
    public ResponseEntity<CursoEventMessage> publicarEvento(@RequestBody CursoEventMessage message) {
        cursoEventPublisher.publicarEvento(message);
        return ResponseEntity.ok(message);
    }
}
