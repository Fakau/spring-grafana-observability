package com.engine.fakau.servicepayment.web;

import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import com.engine.fakau.servicepayment.service.CarteCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carte-credits")
public class CarteCreditController {

    private final CarteCreditService carteCreditService;

    public CarteCreditController(CarteCreditService carteCreditService) {
        this.carteCreditService = carteCreditService;
    }

    @PostMapping
    public ResponseEntity<CarteCreditDTO> createCarteCredit(@RequestBody CarteCreditDTO carteCreditDTO) {
        CarteCreditDTO result = carteCreditService.save(carteCreditDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarteCreditDTO> updateCarteCredit(@PathVariable Long id, @RequestBody CarteCreditDTO carteCreditDTO) {
        CarteCreditDTO result = carteCreditService.update(id, carteCreditDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarteCredit(@PathVariable Long id) {
        carteCreditService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteCreditDTO> getCarteCredit(@PathVariable Long id) {
        CarteCreditDTO result = carteCreditService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<CarteCreditDTO>> getAllCarteCredits() {
        List<CarteCreditDTO> result = carteCreditService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/numero-carte/{numeroCarte}")
    public ResponseEntity<CarteCreditDTO> getByNumeroCarte(@PathVariable String numeroCarte) {
        CarteCreditDTO result = carteCreditService.findByNumeroCarte(numeroCarte);
        return ResponseEntity.ok(result);
    }
}
