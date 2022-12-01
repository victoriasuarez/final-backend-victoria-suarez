package com.finalback.victoriasuarez.catalogservice.event;

import com.finalback.victoriasuarez.catalogservice.configuration.RabbitMQConfig;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class MetricMovieSerieConsumer {


//    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MetricMovieSerieConsumer.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE)
    public void receiveMessageMovie(MetricMovieData message) {
        log.info("Received message as a generic AMQP 'Message' wrapper: {} MOVIE", message.operationId);
//        try {
//            movieRepository.save(objectMapper.readValue(message.operationId, MovieDto.class));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE)
    public void receiveMessageSerie(MetricSerieData message) {
        log.info("Received message as a generic AMQP 'Message' wrapper: {} SERIE", message.operationId);
//        try {
//            serieRepository.save(objectMapper.readValue(message.operationId, SerieDto.class));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Getter
    @Setter
    public static class MetricMovieData {
        private Long id;
        private String operationId;
    }

    @Getter
    @Setter
    public static class MetricSerieData {
        private Long id;
        private String operationId;
    }

}
