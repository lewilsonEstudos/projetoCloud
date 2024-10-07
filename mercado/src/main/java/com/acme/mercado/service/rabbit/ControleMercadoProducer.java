package com.acme.mercado.service.rabbit;


import com.acme.mercado.model.Mercado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.AmqpTemplate;

@Service
public class ControleMercadoProducer {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private final String EXCHANGE = "controleMercado-exc";
    private final String ROUTING_KEY = "controleMercado-rk";

    public ControleMercadoProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(Mercado mercado) throws JsonProcessingException {
        String mensagem = objectMapper.writeValueAsString(mercado);
        amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, mensagem);
    }
}
