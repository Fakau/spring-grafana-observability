package com.engine.fakau.stock.services;

import com.engine.fakau.stock.request.SaleProduitRequet;
import com.engine.fakau.stock.services.dto.ProduitDTO;

import java.util.List;

public interface ProduitService {
    ProduitDTO save(ProduitDTO produitDTO);

    ProduitDTO findById(Long id);

    List<ProduitDTO> findAll();

    void deleteById(Long id);

    void saleProduit(SaleProduitRequet saleProduitRequet);
}
