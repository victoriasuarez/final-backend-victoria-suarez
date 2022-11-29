package com.finalback.victoriasuarez.catalogservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


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
@Data
@Document
@AllArgsConstructor
public class SerieDto {

    @MongoId
    private Long id;
    private String name;
    private String genre;
    public SeasonDto seasons;

}
