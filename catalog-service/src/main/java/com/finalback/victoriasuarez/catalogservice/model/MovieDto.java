package com.finalback.victoriasuarez.catalogservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Data
@Document(collection = "Movies")
public class MovieDto {

    @MongoId
    private Long id;
    private String name;
    private String genre;
    private String urlStream;

}
