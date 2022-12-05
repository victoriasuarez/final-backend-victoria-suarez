package com.finalback.victoriasuarez.catalogservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalback.victoriasuarez.catalogservice.configuration.RabbitMQConfig;
import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import com.finalback.victoriasuarez.catalogservice.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;



@Service
public class MetricMovieConsumer {

    private final ObjectMapper objectMapper;
    private final MovieRepository repository;
    private static final Logger log = LoggerFactory.getLogger(MetricMovieConsumer.class);

    public MetricMovieConsumer(ObjectMapper objectMapper, MovieRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE)
    public void execute(MetricMovieData data) {
        log.info("Music received successfully");
        MovieDto movieDto = new MovieDto();
        objectMapper.convertValue(movieDto, MetricMovieData.class);
        repository.deleteById(data.getId());
        repository.save(movieDto);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetricMovieData {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }

}
