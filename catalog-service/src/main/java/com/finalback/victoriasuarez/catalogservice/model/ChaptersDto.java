package com.finalback.victoriasuarez.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
public class ChaptersDto {

    @MongoId
    private Long id;
    private String name;
    private Integer number;
    private String urlStream;
}
