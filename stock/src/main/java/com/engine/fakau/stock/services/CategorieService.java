package com.engine.fakau.stock.services;

import com.engine.fakau.stock.services.dto.CategorieDTO;

import java.util.List;

public interface CategorieService {
    CategorieDTO save(CategorieDTO categorieDTO);

    CategorieDTO findById(Long id);

    List<CategorieDTO> findAll();

    void deleteById(Long id);
}
