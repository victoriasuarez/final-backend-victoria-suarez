package com.finalback.victoriasuarez.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@AllArgsConstructor
public class SeasonDto {

    @MongoId
    private Long id;
    private Integer seasonNumber;
    public ChaptersDto chapters;
}
