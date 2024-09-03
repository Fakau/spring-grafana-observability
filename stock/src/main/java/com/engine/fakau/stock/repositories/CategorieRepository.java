package com.engine.fakau.stock.repositories;

import com.engine.fakau.stock.entities.CategorieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<CategorieEntity, Long> {
}
