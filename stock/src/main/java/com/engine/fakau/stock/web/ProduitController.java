package com.engine.fakau.stock.web;

import com.engine.fakau.stock.request.SaleProduitRequet;
import com.engine.fakau.stock.services.ProduitService;
import com.engine.fakau.stock.services.dto.ProduitDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ProduitDTO save(@RequestBody ProduitDTO produitDTO) {
        return produitService.save(produitDTO);
    }

    @PostMapping("/sale")
    public void sale(@RequestBody SaleProduitRequet saleProduitRequet) {
        produitService.saleProduit(saleProduitRequet);
    }

    @GetMapping("/{id}")
    public ProduitDTO findById(@PathVariable Long id) {
        return produitService.findById(id);
    }

    @GetMapping
    public List<ProduitDTO> findAll() {
        return produitService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        produitService.deleteById(id);
    }
}
