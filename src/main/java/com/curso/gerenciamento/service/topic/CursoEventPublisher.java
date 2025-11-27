package com.curso.gerenciamento.service.topic;

import com.curso.gerenciamento.dto.CursoEventMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class CursoEventPublisher {

    private static final String TOPIC_NAME = "curso-events";

    private final JmsTemplate jmsTopicTemplate;

    public CursoEventPublisher(@Qualifier("jmsTopicTemplate") JmsTemplate jmsTopicTemplate) {
        this.jmsTopicTemplate = jmsTopicTemplate;
    }

    public void publicarEvento(CursoEventMessage message) {
        message.setEventId(UUID.randomUUID().toString());
        message.setTimestamp(LocalDateTime.now());
        jmsTopicTemplate.convertAndSend(TOPIC_NAME, message);
        log.info("Evento publicado no topic - ID: {}, Tipo: {}", message.getEventId(), message.getTipoEvento());
    }
}
