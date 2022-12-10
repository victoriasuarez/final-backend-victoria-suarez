package com.finalback.victoriasuarez.series.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Serie {

    @MongoId
    private Long id;
    private String name;
    private String genre;
    public SeasonDto seasons;

}
