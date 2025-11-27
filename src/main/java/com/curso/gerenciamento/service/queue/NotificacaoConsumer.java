package com.curso.gerenciamento.service.queue;

import com.curso.gerenciamento.dto.NotificacaoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificacaoConsumer {

    @JmsListener(destination = "notificacao-queue", containerFactory = "queueListenerContainerFactory")
    public void processarNotificacao(NotificacaoMessage message) {
        log.info("Notificacao recebida - ID: {}, Destinatario: {}, Titulo: {}",
                message.getId(),
                message.getDestinatario(),
                message.getTitulo());
    }
}
