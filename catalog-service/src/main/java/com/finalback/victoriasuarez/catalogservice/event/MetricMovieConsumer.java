package com.finalback.victoriasuarez.catalogservice.event;

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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
public class MetricMovieConsumer {

    private final MovieRepository repository;
    private static final Logger log = LoggerFactory.getLogger(MetricMovieConsumer.class);

    public MetricMovieConsumer(MovieRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE)
    public void execute(MetricMovieConsumer.Data data) {
//        log.info("new Movie");
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(data.getMovieData(), movieDto);
        repository.deleteById(data.movieData.getId());
        repository.save(movieDto);
    }

//    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE)
//    public void receivedMessage(final MetricMovieData message) {
//        log.info("Received message as a generic AMQP 'Message' wrapper: {}", message.id);
//    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {

        private MetricMovieData movieData = new MetricMovieData();
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
}
