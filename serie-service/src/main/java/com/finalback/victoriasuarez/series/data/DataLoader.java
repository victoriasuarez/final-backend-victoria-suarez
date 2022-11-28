package com.finalback.victoriasuarez.series.data;

import com.finalback.victoriasuarez.series.model.ChapterDto;
import com.finalback.victoriasuarez.series.model.SeasonDto;
import com.finalback.victoriasuarez.series.model.Serie;
import com.finalback.victoriasuarez.series.model.Serie.*;
import com.finalback.victoriasuarez.series.repository.SerieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private SerieRepository repository;

    public DataLoader (SerieRepository repository) {
        this.repository = repository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        repository.save(new Serie(1L,"La casa de papel","Action", new Serie.SeasonDto()));
//        repository.save(new Serie(2L, "High School Musical: la serie", "Romance"));


        repository.save(new Serie(1L,"La casa de papel","Action", new SeasonDto(1L, 2, new ChapterDto(1L, "Probando", 2,"wwww.netflix.com"))));
    }
}
