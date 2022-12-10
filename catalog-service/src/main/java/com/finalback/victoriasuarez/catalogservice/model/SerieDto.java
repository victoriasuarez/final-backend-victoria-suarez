package com.finalback.victoriasuarez.catalogservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Series")
public class SerieDto implements Serializable {

    @MongoId
    private Long id;
    private String name;
    private String genre;
    public SeasonDto seasons;

}
