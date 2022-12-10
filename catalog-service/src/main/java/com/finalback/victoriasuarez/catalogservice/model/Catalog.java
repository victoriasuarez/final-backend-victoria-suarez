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

//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class MovieData {
//        private Long id;
//        private String name;
//        private String genre;
//        private String urlStream;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class MovieData {
//        private Long id;
//        private String name;
//        private String genre;
//        public SeasonDto seasons;
//    }

}
