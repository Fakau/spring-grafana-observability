package com.engine.fakau.servicepayment.serviceimpltest;

import com.engine.fakau.servicepayment.entities.CarteCreditEntity;
import com.engine.fakau.servicepayment.entities.enumeration.TypeCompte;
import com.engine.fakau.servicepayment.repositories.CarteCreditRepository;
import com.engine.fakau.servicepayment.service.HistoriqueTransactionService;
import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import com.engine.fakau.servicepayment.request.CreerPaiementRequest;
import com.engine.fakau.servicepayment.service.mapper.CarteCreditMapper;
import com.engine.fakau.servicepayment.service.serviceimpl.CarteCreditServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarteCreditServiceImplTest {

    @Mock
    private CarteCreditRepository carteCreditRepository;

    @Mock
    private CarteCreditMapper carteCreditMapper;

    @Mock
    private HistoriqueTransactionService historiqueTransactionService;

    @InjectMocks
    private CarteCreditServiceImpl carteCreditService;

    private CarteCreditDTO carteCreditDTO;
    private CarteCreditEntity carteCreditEntity;

    @BeforeEach
    void setUp() {
        carteCreditDTO = new CarteCreditDTO();
        carteCreditEntity = new CarteCreditEntity();
    }

    @Test
    void testSave() {
        when(carteCreditMapper.toEntity(any(CarteCreditDTO.class))).thenReturn(carteCreditEntity);
        when(carteCreditRepository.save(any(CarteCreditEntity.class))).thenReturn(carteCreditEntity);
        when(carteCreditMapper.toDto(any(CarteCreditEntity.class))).thenReturn(carteCreditDTO);

        CarteCreditDTO result = carteCreditService.save(carteCreditDTO);

        assertNotNull(result);
        verify(carteCreditRepository, times(1)).save(carteCreditEntity);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        when(carteCreditRepository.findById(id)).thenReturn(Optional.of(carteCreditEntity));
        when(carteCreditMapper.toEntity(any(CarteCreditDTO.class))).thenReturn(carteCreditEntity);
        when(carteCreditRepository.save(any(CarteCreditEntity.class))).thenReturn(carteCreditEntity);
        when(carteCreditMapper.toDto(any(CarteCreditEntity.class))).thenReturn(carteCreditDTO);

        CarteCreditDTO result = carteCreditService.update(id, carteCreditDTO);

        assertNotNull(result);
        verify(carteCreditRepository, times(1)).findById(id);
        verify(carteCreditRepository, times(1)).save(carteCreditEntity);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        doNothing().when(carteCreditRepository).deleteById(id);

        carteCreditService.delete(id);

        verify(carteCreditRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(carteCreditRepository.findById(id)).thenReturn(Optional.of(carteCreditEntity));
        when(carteCreditMapper.toDto(any(CarteCreditEntity.class))).thenReturn(carteCreditDTO);

        CarteCreditDTO result = carteCreditService.findById(id);

        assertNotNull(result);
        verify(carteCreditRepository, times(1)).findById(id);
    }

    @Test
    void testFindByNumeroCarte() {
        String numeroCarte = "123456789";
        when(carteCreditRepository.findByNumeroCarteAndTypeCompte(numeroCarte, TypeCompte.CLIENT)).thenReturn(Optional.of(carteCreditEntity));
        when(carteCreditMapper.toDto(any(CarteCreditEntity.class))).thenReturn(carteCreditDTO);

        CarteCreditDTO result = carteCreditService.findByNumeroCarte(numeroCarte);

        assertNotNull(result);
        verify(carteCreditRepository, times(1)).findByNumeroCarteAndTypeCompte(numeroCarte, TypeCompte.CLIENT);
    }

    @Test
    void testFindAll() {
        when(carteCreditRepository.findAll()).thenReturn(List.of(carteCreditEntity));
        when(carteCreditMapper.toDto(any(CarteCreditEntity.class))).thenReturn(carteCreditDTO);

        List<CarteCreditDTO> result = carteCreditService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(carteCreditRepository, times(1)).findAll();
    }

    @Test
    void testCreerPaiement() {
        String numeroMarchant = "123456789";
        String numeroCarte = "987654321";
        Double montant = 100.0;
        String codeSecurite = "123";
        String dateExpiration = LocalDate.now().plusDays(1).toString();
        CreerPaiementRequest creerPaiementDTO = new CreerPaiementRequest(
                numeroMarchant,
                numeroCarte,
                dateExpiration,
                codeSecurite,
                montant);
        CarteCreditEntity cpMarchant = new CarteCreditEntity();
        cpMarchant.setId(1L);
        cpMarchant.setCreditDisponible(BigDecimal.valueOf(1000));
        cpMarchant.setDateExpiration(LocalDate.now().plusDays(1));

        CarteCreditEntity cpClient = new CarteCreditEntity();
        cpClient.setId(2L);
        cpClient.setCreditDisponible(BigDecimal.valueOf(500));
        cpClient.setDateExpiration(LocalDate.now().plusDays(1));
        cpClient.setCodeSecurite("123");

        when(carteCreditRepository.findByNumeroCarteAndTypeCompte(creerPaiementDTO.numeroMarchant(), TypeCompte.MARCHANT)).thenReturn(Optional.of(cpMarchant));
        when(carteCreditRepository.findByNumeroCarteAndTypeCompte(creerPaiementDTO.numeroCarte(), TypeCompte.CLIENT)).thenReturn(Optional.of(cpClient));

        carteCreditService.creerPaiement(creerPaiementDTO);

        verify(carteCreditRepository, times(1)).save(cpClient);
        verify(carteCreditRepository, times(1)).save(cpMarchant);

        ArgumentCaptor<CarteCreditDTO> clientCaptor = ArgumentCaptor.forClass(CarteCreditDTO.class);
        ArgumentCaptor<CarteCreditDTO> marchantCaptor = ArgumentCaptor.forClass(CarteCreditDTO.class);
        ArgumentCaptor<BigDecimal> montantCaptor = ArgumentCaptor.forClass(BigDecimal.class);

        verify(historiqueTransactionService, times(1)).save(clientCaptor.capture(), marchantCaptor.capture(), montantCaptor.capture());

        assertEquals(carteCreditMapper.toDto(cpClient), clientCaptor.getValue());
        assertEquals(carteCreditMapper.toDto(cpMarchant), marchantCaptor.getValue());
        assertEquals(BigDecimal.valueOf(montant), montantCaptor.getValue());
    }
}
