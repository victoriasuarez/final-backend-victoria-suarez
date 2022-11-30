package com.finalback.victoriasuarez.series.event;

import com.finalback.victoriasuarez.series.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MetricSerieCatalogProducer {

    public static final Logger log = LoggerFactory.getLogger(MetricSerieCatalogProducer.class);

    private final RabbitTemplate rabbitTemplate;


    public MetricSerieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMesagge(MetricSerieCatalogData data) {
        log.info("Sending message desde series...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_METRIC_CATALOG, data);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MetricSerieCatalogData {
        private Long id;
        private String operationId;
    }

}
