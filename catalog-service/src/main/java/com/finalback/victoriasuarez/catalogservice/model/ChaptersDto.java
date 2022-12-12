package com.finalback.victoriasuarez.catalogservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChaptersDto implements Serializable {

    @MongoId
    private Long id;
    private String name;
    private Integer number;
    private String urlStream;
}
