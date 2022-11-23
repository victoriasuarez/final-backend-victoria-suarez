package com.parcial.victoriasuarez.catalogservice.client;

import com.parcial.victoriasuarez.catalogservice.configuration.LoadBalancerConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(value = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = LoadBalancerConfiguration.class)
public interface MovieClient {

    @Getter
    @Setter
    class MovieDto {
        private Long movieId;
        private String name;
        private String genre;
        private String urlStream;
    }

    @GetMapping("/movies/{id}")
    MovieDto getById(@PathVariable Long id);

    @GetMapping("/movies/{genre}")
    List<MovieDto> getByGenre(@PathVariable(value = "genre") String genre);
}
