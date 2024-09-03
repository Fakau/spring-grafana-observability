package com.engine.fakau.servicepayment.repositories;

import com.engine.fakau.servicepayment.entities.HistoriqueTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueTransactionRepository extends JpaRepository<HistoriqueTransactionEntity, Long> {
    List<HistoriqueTransactionEntity> findByCarteCreditNumeroCarte(String id);
}
