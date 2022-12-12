package com.finalback.victoriasuarez.catalogservice.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {

    private List<MovieDto> movies = new ArrayList<>();
    private List<SerieDto> series = new ArrayList<>();

}
