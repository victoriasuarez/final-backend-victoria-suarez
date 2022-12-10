package com.finalback.victoriasuarez.catalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class SeasonDto implements Serializable {

    @MongoId
    private Long id;
    private Integer seasonNumber;
    public ChaptersDto chapters;
}
