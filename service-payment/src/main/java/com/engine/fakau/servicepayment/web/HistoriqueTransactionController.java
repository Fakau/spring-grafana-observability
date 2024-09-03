package com.engine.fakau.servicepayment.web;

import com.engine.fakau.servicepayment.service.HistoriqueTransactionService;
import com.engine.fakau.servicepayment.service.dto.HistoriqueTransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historique-transactions")
public class HistoriqueTransactionController {

    private final HistoriqueTransactionService service;

    public HistoriqueTransactionController(HistoriqueTransactionService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueTransactionDTO> getById(@PathVariable Long id) {
        HistoriqueTransactionDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<HistoriqueTransactionDTO>> getAll() {
        List<HistoriqueTransactionDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/carte-credit/{numeroCarte}")
    public ResponseEntity<List<HistoriqueTransactionDTO>> getByCarteCreditId(@PathVariable String numeroCarte) {
        List<HistoriqueTransactionDTO> list = service.findByCarteCreditNumeroCarte(numeroCarte);
        return ResponseEntity.ok(list);
    }
}
