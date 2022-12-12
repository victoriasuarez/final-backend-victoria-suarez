package com.finalback.victoriasuarez.catalogservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeasonDto implements Serializable {

    @MongoId
    private Long id;
    private Integer seasonNumber;
    private ChaptersDto chapters;

}
