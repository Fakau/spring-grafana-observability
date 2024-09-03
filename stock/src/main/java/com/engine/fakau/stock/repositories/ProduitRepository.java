package com.engine.fakau.stock.repositories;

import com.engine.fakau.stock.entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<ProduitEntity, Long> {
}
