package com.engine.fakau.servicepayment.repositories;

import com.engine.fakau.servicepayment.entities.CarteCreditEntity;
import com.engine.fakau.servicepayment.entities.enumeration.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarteCreditRepository extends JpaRepository<CarteCreditEntity, Long> {
    Optional<CarteCreditEntity> findByNumeroCarteAndTypeCompte(String numeroCarte, TypeCompte typeCompte);
}
