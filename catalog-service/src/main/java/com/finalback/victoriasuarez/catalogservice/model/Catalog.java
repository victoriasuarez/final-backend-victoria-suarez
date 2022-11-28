package com.finalback.victoriasuarez.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
public class Catalog {

    List<MovieDto> movies;
    List<SerieDto> series;

}
