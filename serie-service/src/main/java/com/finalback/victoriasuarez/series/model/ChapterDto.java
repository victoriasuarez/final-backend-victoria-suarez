package com.finalback.victoriasuarez.series.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDto {

    @MongoId
    private Long id;
    private String name;
    private Integer number;
    private String urlStream;
}
