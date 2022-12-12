package com.finalback.victoriasuarez.series.event;

import com.finalback.victoriasuarez.series.config.RabbitMQConfig;
import com.finalback.victoriasuarez.series.model.Serie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NewSerieCatalogProducer {

    private final RabbitTemplate rabbitTemplate;


    public NewSerieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Serie newSerie) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_SERIE, newSerie);
    }

}
