package com.finalback.victoriasuarez.series.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@AllArgsConstructor
public class Serie {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ChaptersDto {
        private Long id;
        private String name;
        private Integer number;
        private String urlStream;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class SeasonDto {
        private Long id;
        private Integer seasonNumber;
        public ChaptersDto chapters;
    }


    @MongoId
    private Long id;
    private String name;
    private String genre;
    public SeasonDto seasons;

}
