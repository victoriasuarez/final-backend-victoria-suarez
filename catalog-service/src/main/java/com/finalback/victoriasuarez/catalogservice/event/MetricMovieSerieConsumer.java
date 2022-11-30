package com.finalback.victoriasuarez.catalogservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalback.victoriasuarez.catalogservice.configuration.RabbitMQConfig;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.repository.MovieRepository;
import com.finalback.victoriasuarez.catalogservice.repository.SerieRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;


@Service
public class MetricMovieSerieConsumer {

    private final MovieRepository movieRepository;

    private final SerieRepository serieRepository;

//    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MetricMovieSerieConsumer.class);

    public MetricMovieSerieConsumer(MovieRepository movieRepository, SerieRepository serieRepository) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
    }

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
