package br.com.acme.controleMercado.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class controleMercado {
    private final String QUEUE = "controleMercado-queue";

    @RabbitListener(queues = { QUEUE })
    public void receive(@Payload String message) {
        log.info("ControleMercadoWorker received: {}", message);
            }
}