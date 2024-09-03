package com.engine.fakau.servicepayment.service.serviceimpl;

import com.engine.fakau.servicepayment.entities.enumeration.TypeCompte;
import com.engine.fakau.servicepayment.exception.*;
import com.engine.fakau.servicepayment.service.HistoriqueTransactionService;
import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import com.engine.fakau.servicepayment.entities.CarteCreditEntity;
import com.engine.fakau.servicepayment.repositories.CarteCreditRepository;
import com.engine.fakau.servicepayment.service.CarteCreditService;
import com.engine.fakau.servicepayment.request.CreerPaiementRequest;
import com.engine.fakau.servicepayment.service.mapper.CarteCreditMapper;
import org.springframework.stereotype.Service;
import static com.engine.fakau.servicepayment.utils.Constants.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarteCreditServiceImpl implements CarteCreditService {

    private final CarteCreditRepository carteCreditRepository;

    private final CarteCreditMapper carteCreditMapper;

    private final HistoriqueTransactionService historiqueTransactionService;

    public CarteCreditServiceImpl(CarteCreditRepository carteCreditRepository, CarteCreditMapper carteCreditMapper, HistoriqueTransactionService historiqueTransactionService) {
        this.carteCreditRepository = carteCreditRepository;
        this.carteCreditMapper = carteCreditMapper;
        this.historiqueTransactionService = historiqueTransactionService;
    }

    @Override
    public CarteCreditDTO save(CarteCreditDTO carteCreditDTO) {
        CarteCreditEntity entity = carteCreditMapper.toEntity(carteCreditDTO);
        entity = carteCreditRepository.save(entity);
        return carteCreditMapper.toDto(entity);
    }

    @Override
    public CarteCreditDTO update(Long id, CarteCreditDTO carteCreditDTO) {
        CarteCreditEntity entity = carteCreditRepository.findById(id).orElseThrow(() -> new RuntimeException("CarteCredit not found"));
        entity = carteCreditMapper.toEntity(carteCreditDTO);
        entity.setId(id);
        entity = carteCreditRepository.save(entity);
        return carteCreditMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        carteCreditRepository.deleteById(id);
    }

    @Override
    public CarteCreditDTO findById(Long id) {
        return carteCreditRepository.findById(id)
                .map(carteCreditMapper::toDto)
                .orElseThrow(() -> new CarteCreditNotFoundException(CARTE_CREDIT_NON_TROUVEE));
    }

    @Override
    public CarteCreditDTO findByNumeroCarte(String numeroCarte) {
        return carteCreditRepository.findByNumeroCarteAndTypeCompte(numeroCarte, TypeCompte.CLIENT)
                .map(carteCreditMapper::toDto)
                .orElseThrow(() -> new CarteCreditNotFoundException(CARTE_CREDIT_NON_TROUVEE));
    }

    @Override
    public List<CarteCreditDTO> findAll() {
        return carteCreditRepository.findAll().stream()
                .map(carteCreditMapper::toDto)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void creerPaiement(CreerPaiementRequest creerPaiementDTO) {
        CarteCreditEntity cpMarchant = carteCreditRepository.findByNumeroCarteAndTypeCompte(creerPaiementDTO.numeroMarchant(), TypeCompte.MARCHANT)
                .orElseThrow(() -> new CompteMarchantNotFoundException(CONTACTER_MARCHANT));
        CarteCreditEntity cpClient = carteCreditRepository.findByNumeroCarteAndTypeCompte(creerPaiementDTO.numeroCarte(), TypeCompte.CLIENT)
                .orElseThrow(() -> new CompteMarchantNotFoundException(CARTE_CREDIT_NON_TROUVEE));
        isValide(cpMarchant, cpClient, creerPaiementDTO);
        cpClient.setCreditDisponible(cpClient.getCreditDisponible().subtract(BigDecimal.valueOf(creerPaiementDTO.montant())));
        cpClient = carteCreditRepository.save(cpClient);
        cpMarchant.setCreditDisponible(cpMarchant.getCreditDisponible().add(BigDecimal.valueOf(creerPaiementDTO.montant())));
        cpMarchant = carteCreditRepository.save(cpMarchant);
        historiqueTransactionService.save(carteCreditMapper.toDto(cpClient), carteCreditMapper.toDto(cpMarchant), BigDecimal.valueOf(creerPaiementDTO.montant()));
    }

    private void isValide(CarteCreditEntity cpMarchant, CarteCreditEntity cpClient, CreerPaiementRequest creerPaiementDTO) {
        if (!cpClient.getCodeSecurite().equals(creerPaiementDTO.codeSecurite())) {
            throw new InformationCarteIncorrectException(INFORMATION_INCORRECTE);
        }
        if (cpClient.getDateExpiration().isBefore(LocalDate.now())) {
            throw new CarteNonValideException(CARTE_CREDIT_NON_VALIDE);
        }
        if (cpClient.getCreditDisponible().compareTo(BigDecimal.valueOf(creerPaiementDTO.montant())) < 0) {
            throw new CreditInsuffisantException(CREDIT_INSUFFUSANT);
        }
        if (cpMarchant.getDateExpiration().isBefore(LocalDate.now())) {
            throw new ErreurMarchantException(CONTACTER_MARCHANT);
        }
    }
}
