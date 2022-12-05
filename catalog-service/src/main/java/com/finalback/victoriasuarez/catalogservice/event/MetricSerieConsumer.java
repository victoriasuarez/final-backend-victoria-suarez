package com.finalback.victoriasuarez.catalogservice.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalback.victoriasuarez.catalogservice.configuration.RabbitMQConfig;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.repository.SerieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MetricSerieConsumer {

    private final SerieRepository repository;
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(MetricSerieConsumer.class);

    public MetricSerieConsumer(SerieRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE)
    public void execute(MetricSerieData data) {
        log.info("Serie received successfully");
        SerieDto serieDto = new SerieDto();
        objectMapper.convertValue(serieDto, MetricSerieData.class);
        repository.deleteById(data.getId());
        repository.save(serieDto);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetricSerieData {
        private Long id;
        private String name;
        private String genre;
        public SeasonDto seasons;

            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            class ChaptersDto {

                private Long id;
                private String name;
                private Integer number;
                private String urlStream;
            }


                @Getter
                @Setter
                @NoArgsConstructor
                @AllArgsConstructor
                class SeasonDto {
                    private Long id;
                    private Integer seasonNumber;
                    public ChaptersDto chapters;
                }
    }

}
