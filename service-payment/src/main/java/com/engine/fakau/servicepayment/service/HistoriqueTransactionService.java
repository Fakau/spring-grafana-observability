package com.engine.fakau.servicepayment.service;

import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import com.engine.fakau.servicepayment.service.dto.HistoriqueTransactionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface HistoriqueTransactionService {
    HistoriqueTransactionDTO save(CarteCreditDTO carteCredit, CarteCreditDTO compteMarchant, BigDecimal montant);

    HistoriqueTransactionDTO findById(Long id);

    List<HistoriqueTransactionDTO> findAll();

    List<HistoriqueTransactionDTO> findByCarteCreditNumeroCarte(String numeroCarte);
}
