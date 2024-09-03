package com.engine.fakau.stock.repositoriesTest;


import com.engine.fakau.stock.entities.CategorieEntity;
import com.engine.fakau.stock.repositories.CategorieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CategorieRepositoryTest {

    @Autowired
    private CategorieRepository categorieRepository;

    @Test
    public void testFindById() {
        CategorieEntity categorie = new CategorieEntity();
        categorie.setLibelle("Test Libelle");
        categorie.setDescription("Test Description");
        categorie = categorieRepository.save(categorie);

        Optional<CategorieEntity> found = categorieRepository.findById(categorie.getId());
        assertTrue(found.isPresent());
    }
}
