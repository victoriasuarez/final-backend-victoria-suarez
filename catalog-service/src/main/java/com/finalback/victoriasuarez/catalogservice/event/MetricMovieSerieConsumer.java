package com.finalback.victoriasuarez.catalogservice.event;


import com.finalback.victoriasuarez.catalogservice.config.RabbitMQConfig;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MetricMovieSerieConsumer {

    private static final Logger log = LoggerFactory.getLogger(MetricMovieSerieConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE)
    public void receiveMessage(final MetricMovieSerieData message) {
        log.info("Creado", message.operationId);
    }

    @Getter
    @Setter
    public static class MetricMovieSerieData {
        private Long id;
        private String operationId;
    }
}
