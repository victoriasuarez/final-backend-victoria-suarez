package com.finalback.victoriasuarez.series.event;

import com.finalback.victoriasuarez.series.config.RabbitMQConfig;
import com.finalback.victoriasuarez.series.model.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class MetricSerieCatalogProducer {

//    public static final Logger log = LoggerFactory.getLogger(MetricSerieCatalogProducer.class);

    private final RabbitTemplate rabbitTemplate;


    public MetricSerieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Serie newSerie) {
        MetricSerieCatalogProducer.Data data = new MetricSerieCatalogProducer.Data();
        BeanUtils.copyProperties(newSerie, data.getSeries());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_SERIE, data);
    }

//    public void sendMessage(MetricSerieData dataSerie) {
//        log.info("Sending message desde series...");
//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_SERIE, dataSerie);
//    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data implements Serializable {

        private MetricSerieData series = new MetricSerieData();

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MetricSerieData {
            private Long id;
            private String name;
            private String genre;
            public SeasonDto seasons;


            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SeasonDto {
                private Long id;
                private Integer seasonNumber;
                public ChaptersDto chapters;
            }


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

        }
    }
}
