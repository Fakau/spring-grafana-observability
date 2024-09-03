package com.engine.fakau.stock;

import com.engine.fakau.stock.entities.CategorieEntity;
import com.engine.fakau.stock.entities.ProduitEntity;
import com.engine.fakau.stock.repositories.CategorieRepository;
import com.engine.fakau.stock.repositories.ProduitRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

    @Autowired
    CategorieRepository categorieRepository;

    @Autowired
    ProduitRepository produitRepository;

    @Bean
    public List<CategorieEntity> initCat() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<List<CategorieEntity>> typeReference = new TypeReference<List<CategorieEntity>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data-cat.json");
        try {
            List<CategorieEntity> categorieEntities = mapper.readValue(inputStream, typeReference);
            return categorieRepository.saveAll(categorieEntities);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save cat: " + e.getMessage());
        }
    }

    @Bean
    public List<ProduitEntity> initProd() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        TypeReference<List<ProduitEntity>> typeReference = new TypeReference<List<ProduitEntity>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data-pro.json");
        try {
            List<ProduitEntity> produitEntities = mapper.readValue(inputStream, typeReference);
            return produitRepository.saveAll(produitEntities);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save prod: " + e.getMessage());
        }
    }
}
