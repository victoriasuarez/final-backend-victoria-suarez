package com.finalback.victoriasuarez.catalogservice.client;

import com.finalback.victoriasuarez.catalogservice.configuration.LoadBalancerConfiguration;
import com.finalback.victoriasuarez.catalogservice.model.SerieDto;
import lombok.*;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(value = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = LoadBalancerConfiguration.class)
public interface SerieClient {

    @GetMapping("/series/{id}")
    SerieDto getById(@PathVariable Long id);

    @GetMapping("/series/{genre}")
    List<SerieDto> getByGenreSerie(@PathVariable(value = "genre") String genre);

}
