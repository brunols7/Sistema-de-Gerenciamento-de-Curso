package com.curso.gerenciamento.service.topic;

import com.curso.gerenciamento.dto.CursoEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CursoEventSubscriber {

    @JmsListener(destination = "curso-events", containerFactory = "topicListenerContainerFactory")
    public void onCursoEvent(CursoEventMessage message) {
        log.info("Evento recebido - ID: {}, Tipo: {}, Curso: {} (ID: {})",
                message.getEventId(),
                message.getTipoEvento(),
                message.getCursoTitulo(),
                message.getCursoId());
    }
}
