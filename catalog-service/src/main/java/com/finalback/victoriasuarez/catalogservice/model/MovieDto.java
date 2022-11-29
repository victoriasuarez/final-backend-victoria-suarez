package com.finalback.victoriasuarez.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Data
@Document
@RequiredArgsConstructor
public class MovieDto {

    @MongoId
    private Long id;
    private String name;
    private String genre;
    private String urlStream;

}
