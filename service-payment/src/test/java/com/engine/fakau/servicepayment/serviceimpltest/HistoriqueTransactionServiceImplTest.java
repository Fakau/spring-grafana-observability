package com.engine.fakau.servicepayment.serviceimpltest;

import com.engine.fakau.servicepayment.repositories.HistoriqueTransactionRepository;
import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import com.engine.fakau.servicepayment.service.dto.HistoriqueTransactionDTO;
import com.engine.fakau.servicepayment.service.mapper.HistoriqueTransactionMapper;
import com.engine.fakau.servicepayment.service.serviceimpl.HistoriqueTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoriqueTransactionServiceImplTest {

    @Mock
    private HistoriqueTransactionRepository repository;

    @Mock
    private HistoriqueTransactionMapper mapper;

    @InjectMocks
    private HistoriqueTransactionServiceImpl service;

    private CarteCreditDTO carteCreditDTO;
    private CarteCreditDTO compteMarchantDTO;
    private HistoriqueTransactionDTO historiqueTransactionDTO;

    @BeforeEach
    void setUp() {
        carteCreditDTO = new CarteCreditDTO();
        compteMarchantDTO = new CarteCreditDTO();
        historiqueTransactionDTO = new HistoriqueTransactionDTO();
        historiqueTransactionDTO.setCarteCredit(carteCreditDTO);
        historiqueTransactionDTO.setCompteMarchand(compteMarchantDTO);
        historiqueTransactionDTO.setDateTransaction(LocalDateTime.now());
        historiqueTransactionDTO.setMontant(BigDecimal.valueOf(100));
        historiqueTransactionDTO.setNumeroTransaction("TRX" + LocalDateTime.now().toString());
    }

    @Test
    void findByIdShouldReturnNullWhenNotExists() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        HistoriqueTransactionDTO result = service.findById(id);

        assertNull(result);
        verify(repository, times(1)).findById(id);
    }
}
