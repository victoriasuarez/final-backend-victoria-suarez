package com.finalback.victoriasuarez.catalogservice.event;

import com.finalback.victoriasuarez.catalogservice.configuration.RabbitMQConfig;
import com.finalback.victoriasuarez.catalogservice.model.SeasonDto;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.repository.SerieRepository;
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
public class MetricSerieConsumer {

    private final SerieRepository repository;
    private static final Logger log = LoggerFactory.getLogger(MetricSerieConsumer.class);

    public MetricSerieConsumer(SerieRepository repository) {
        this.repository = repository;
    }


    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE)
    public void execute(MetricSerieConsumer.Data data) {
//        log.info("new Serie");
        SerieDto serieDto = new SerieDto();
        BeanUtils.copyProperties(data.getSerieData(), serieDto);
        repository.deleteById(data.serieData.getId());
        repository.save(serieDto);
    }

//    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE)
//    public void receivedMessage(final MetricSerieData message) {
//        log.info("Received message as a generic AMQP 'Message' wrapper: {}", message.id);
//    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data implements Serializable {

        private MetricSerieData serieData = new MetricSerieData();

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
                        public static class ChaptersDto {

                            private Long id;
                            private String name;
                            private Integer number;
                            private String urlStream;
                        }


                            @Getter
                            @Setter
                            @NoArgsConstructor
                            @AllArgsConstructor
                            public static class SeasonDto {
                                private Long id;
                                private Integer seasonNumber;
                                public ChaptersDto chapters;
                            }
        }
}

}
