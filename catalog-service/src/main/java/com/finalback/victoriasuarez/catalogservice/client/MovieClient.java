package com.finalback.victoriasuarez.catalogservice.client;

import com.finalback.victoriasuarez.catalogservice.configuration.LoadBalancerConfiguration;

import com.finalback.victoriasuarez.catalogservice.model.MovieDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@FeignClient(value = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = LoadBalancerConfiguration.class)
public interface MovieClient {

    @GetMapping("/movies")
    List<MovieDto> getAll();

    @GetMapping("/movies/{id}")
    MovieDto getById(@PathVariable Long id);

    @GetMapping("/movies/{genre}")
    List<MovieDto> getByGenreMovie(@PathVariable(value = "genre") String genre);
}
