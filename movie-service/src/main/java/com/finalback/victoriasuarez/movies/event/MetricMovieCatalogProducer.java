package com.finalback.victoriasuarez.movies.event;

import com.finalback.victoriasuarez.movies.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MetricMovieCatalogProducer {

    public static final Logger log = LoggerFactory.getLogger(MetricMovieCatalogProducer.class);

    private final RabbitTemplate rabbitTemplate;


    public MetricMovieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMesagge(MetricMovieCatalogData data) {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_METRIC_CATALOG, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetricMovieCatalogData {
        private Long movieId;
        private String operationId;
    }


}
