package com.curso.gerenciamento.service.queue;

import com.curso.gerenciamento.dto.NotificacaoMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacaoProducer {

    private static final String QUEUE_NAME = "notificacao-queue";

    private final JmsTemplate jmsTemplate;

    public void enviarNotificacao(NotificacaoMessage message) {
        message.setId(UUID.randomUUID().toString());
        message.setDataEnvio(LocalDateTime.now());
        jmsTemplate.convertAndSend(QUEUE_NAME, message);
        log.info("Notificacao enviada para a fila: {}", message.getId());
    }
}
