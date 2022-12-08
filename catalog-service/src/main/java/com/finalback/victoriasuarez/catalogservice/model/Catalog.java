package com.finalback.victoriasuarez.catalogservice.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {

    List<Catalog.Movies> movies = new ArrayList<>();
    List<Catalog.Series> series = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Movies {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Series {
        private Long id;
        private String name;
        private String genre;
        public SeasonDto seasons;
    }

}
