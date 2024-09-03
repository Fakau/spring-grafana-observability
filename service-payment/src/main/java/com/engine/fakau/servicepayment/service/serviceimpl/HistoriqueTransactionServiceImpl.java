package com.engine.fakau.servicepayment.service.serviceimpl;

import com.engine.fakau.servicepayment.repositories.HistoriqueTransactionRepository;
import com.engine.fakau.servicepayment.service.HistoriqueTransactionService;
import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import com.engine.fakau.servicepayment.service.dto.HistoriqueTransactionDTO;
import com.engine.fakau.servicepayment.service.mapper.HistoriqueTransactionMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoriqueTransactionServiceImpl implements HistoriqueTransactionService {

    private final HistoriqueTransactionRepository repository;

    private final HistoriqueTransactionMapper mapper;

    public HistoriqueTransactionServiceImpl(HistoriqueTransactionRepository repository, HistoriqueTransactionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HistoriqueTransactionDTO save(CarteCreditDTO carteCredit, CarteCreditDTO compteMarchant, BigDecimal montant) {
        HistoriqueTransactionDTO historiqueTransactionDTO = new HistoriqueTransactionDTO();
        historiqueTransactionDTO.setCarteCredit(carteCredit);
        historiqueTransactionDTO.setCompteMarchand(compteMarchant);
        historiqueTransactionDTO.setDateTransaction(LocalDateTime.now());
        historiqueTransactionDTO.setMontant(montant);
        historiqueTransactionDTO.setNumeroTransaction("TRX" + LocalDateTime.now().toString());
        return mapper.toDto(repository.save(mapper.toEntity(historiqueTransactionDTO)));
    }

    @Override
    public HistoriqueTransactionDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    public List<HistoriqueTransactionDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public List<HistoriqueTransactionDTO> findByCarteCreditNumeroCarte(String numeroCarte) {
        return repository.findByCarteCreditNumeroCarte(numeroCarte)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
