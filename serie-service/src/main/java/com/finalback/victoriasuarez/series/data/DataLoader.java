package com.finalback.victoriasuarez.series.data;

import com.finalback.victoriasuarez.series.model.Serie;
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
        repository.save(new Serie(1L,"La casa de papel","Action"));
        repository.save(new Serie(2L, "High School Musical: la serie", "Romance"));
    }
}
