package com.engine.fakau.stock.web;

import com.engine.fakau.stock.services.CategorieService;
import com.engine.fakau.stock.services.dto.CategorieDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @PostMapping
    public CategorieDTO save(@RequestBody CategorieDTO categorieDTO) {
        return categorieService.save(categorieDTO);
    }

    @GetMapping("/{id}")
    public CategorieDTO findById(@PathVariable Long id) {
        return categorieService.findById(id);
    }

    @GetMapping
    public List<CategorieDTO> findAll() {
        return categorieService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categorieService.deleteById(id);
    }
}
