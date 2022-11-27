package com.finalback.victoriasuarez.catalogservice.client;

import com.finalback.victoriasuarez.catalogservice.configuration.LoadBalancerConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = LoadBalancerConfiguration.class)
public interface SerieClient {

    @Getter
    @Setter
    @AllArgsConstructor
    class ChaptersDto {

        @MongoId
        private Long id;
        private String name;
        private Integer number;
        private String urlStream;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class SeasonDto {

        @MongoId
        private Long id;
        private Integer seasonNumber;
        public ChaptersDto chapters;
    }


    @Getter
    @Setter
    class SerieDto {

        @MongoId
        private Long id;
        private String name;
        private String genre;
        public SeasonDto seasons;
    }

    @GetMapping("/series/{id}")
    SerieDto getById(@PathVariable Long id);

    @GetMapping("/series/{genre}")
    List<SerieDto> getByGenreSerie(@PathVariable(value = "genre") String genre);

}
