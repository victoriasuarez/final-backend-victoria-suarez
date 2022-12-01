package com.finalback.victoriasuarez.catalogservice.service;


import com.finalback.victoriasuarez.catalogservice.model.Catalog;
import com.finalback.victoriasuarez.catalogservice.repository.CatalogRepository;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    private final CatalogRepository repository;

    public CatalogService(CatalogRepository repository) {
        this.repository = repository;
    }

    public Catalog save(Catalog catalog) {
        return repository.save(catalog);
    }

}
