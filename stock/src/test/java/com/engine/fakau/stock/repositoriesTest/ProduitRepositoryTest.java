package com.engine.fakau.stock.repositoriesTest;

import com.engine.fakau.stock.entities.ProduitEntity;
import com.engine.fakau.stock.repositories.ProduitRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProduitRepositoryTest {

    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testFindById() {
        ProduitEntity produit = new ProduitEntity();
        produit.setLibelle("Test Libelle");
        produit.setDescription("Test Description");
        produit.setPrix(100.0);
        produit.setQuantite(10);
        produit = produitRepository.save(produit);

        Optional<ProduitEntity> found = produitRepository.findById(produit.getId());
        assertTrue(found.isPresent());
    }
}
