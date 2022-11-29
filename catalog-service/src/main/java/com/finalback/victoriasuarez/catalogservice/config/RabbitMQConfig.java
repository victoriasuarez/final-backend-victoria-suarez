package com.finalback.victoriasuarez.catalogservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "final_backend_victoria_suarez";

    public static final String QUEUE_MOVIE = "movieMetricsQueue";

    public static final String QUEUE_SERIE = "serieMetricsQueue";

    public static final String ROUTING_KEY_METRIC_CATALOG = "metric.catalog";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue movieQueue() {
        return new Queue(QUEUE_MOVIE, true);
    }

    @Bean
    public Queue serieQueue() {
        return new Queue(QUEUE_SERIE, true);
    }

    @Bean
    public Binding declareBindingMovie() {
        return BindingBuilder.bind(movieQueue()).to(appExchange()).with(ROUTING_KEY_METRIC_CATALOG);
    }

    @Bean
    public Binding declareBindingSerie() {
        return BindingBuilder.bind(serieQueue()).to(appExchange()).with(ROUTING_KEY_METRIC_CATALOG);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
