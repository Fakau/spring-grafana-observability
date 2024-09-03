package com.engine.fakau.servicepayment.service;

import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import com.engine.fakau.servicepayment.request.CreerPaiementRequest;

import java.util.List;

public interface CarteCreditService {
    CarteCreditDTO save(CarteCreditDTO carteCreditDTO);
    CarteCreditDTO update(Long id, CarteCreditDTO carteCreditDTO);
    void delete(Long id);
    CarteCreditDTO findById(Long id);
    CarteCreditDTO findByNumeroCarte(String numeroCarte);
    List<CarteCreditDTO> findAll();
    void creerPaiement(CreerPaiementRequest creerPaiementDTO);
}
