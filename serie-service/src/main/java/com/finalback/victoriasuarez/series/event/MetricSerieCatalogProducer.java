package com.finalback.victoriasuarez.series.event;

import com.finalback.victoriasuarez.series.config.RabbitMQConfig;
import com.finalback.victoriasuarez.series.model.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MetricSerieCatalogProducer {

    public static final Logger log = LoggerFactory.getLogger(MetricSerieCatalogProducer.class);

    private final RabbitTemplate rabbitTemplate;


    public MetricSerieCatalogProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void execute(Serie newSerie) {
        log.info("Sending message desde series...");
        MetricSerieCatalogProducer.MetricSerieData data = new MetricSerieCatalogProducer.MetricSerieData();
        BeanUtils.copyProperties(newSerie, data.getClass());
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_SERIE, data);
    }

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
