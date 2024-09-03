package com.engine.fakau.stock.services.ServiceImpl;

import com.engine.fakau.stock.entities.CategorieEntity;
import com.engine.fakau.stock.repositories.CategorieRepository;
import com.engine.fakau.stock.services.CategorieService;
import com.engine.fakau.stock.services.dto.CategorieDTO;
import com.engine.fakau.stock.services.mappers.CategorieMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;

    private final CategorieMapper categorieMapper;

    public CategorieServiceImpl(CategorieRepository categorieRepository, CategorieMapper categorieMapper) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
    }

    @Override
    public CategorieDTO save(CategorieDTO categorieDTO) {
        CategorieEntity categorieEntity = categorieMapper.toEntity(categorieDTO);
        categorieEntity = categorieRepository.save(categorieEntity);
        return categorieMapper.toDto(categorieEntity);
    }

    @Override
    public CategorieDTO findById(Long id) {
        return categorieRepository.findById(id)
                .map(categorieMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<CategorieDTO> findAll() {
        return categorieRepository.findAll().stream()
                .map(categorieMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        categorieRepository.deleteById(id);
    }
}
