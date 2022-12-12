package com.finalback.victoriasuarez.catalogservice.event;

import com.finalback.victoriasuarez.catalogservice.configuration.RabbitMQConfig;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import com.finalback.victoriasuarez.catalogservice.repository.SerieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class NewSerieConsumer {

    private final SerieRepository repository;
    private static final Logger log = LoggerFactory.getLogger(NewSerieConsumer.class);

    public NewSerieConsumer(SerieRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE)
    public void execute(SerieDto data) {
        log.info("new Serie");
        repository.deleteById(data.getId());
        repository.save(data);
    }

}
