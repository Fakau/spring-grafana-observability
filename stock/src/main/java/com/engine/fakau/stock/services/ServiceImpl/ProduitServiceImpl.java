package com.engine.fakau.stock.services.ServiceImpl;

import com.engine.fakau.stock.entities.ProduitEntity;
import com.engine.fakau.stock.exception.ProduitNotFoundException;
import com.engine.fakau.stock.exception.StockInsuffisant;
import com.engine.fakau.stock.repositories.ProduitRepository;
import com.engine.fakau.stock.request.ProduitRequest;
import com.engine.fakau.stock.request.SaleProduitRequet;
import com.engine.fakau.stock.services.ProduitService;
import com.engine.fakau.stock.services.dto.ProduitDTO;
import com.engine.fakau.stock.services.mappers.ProduitMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    private final ProduitMapper produitMapper;

    private final WebClient webClient;

    @Value("${service.payment.numeroMarchant}")
    private String numeroMarchant;

    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMapper produitMapper, WebClient.Builder webClient) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
        this.webClient = webClient.build();
    }

    @Override
    public ProduitDTO save(ProduitDTO produitDTO) {
        ProduitEntity produitEntity = produitMapper.toEntity(produitDTO);
        produitEntity = produitRepository.save(produitEntity);
        return produitMapper.toDto(produitEntity);
    }


    @Override
    public ProduitDTO findById(Long id) {
        return produitRepository.findById(id)
                .map(produitMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ProduitDTO> findAll() {
        return produitRepository.findAll().stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public void saleProduit(SaleProduitRequet saleProduitRequet) {
        List<ProduitEntity> produits = produitRepository
                .findAllById(saleProduitRequet.produitRequests().stream().map(ProduitRequest::id).collect(Collectors.toList()));
        validerProduit(produits, saleProduitRequet);
        webClient.post()
                .uri("/paiement")
                .bodyValue(getStringData(saleProduitRequet, produits))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        updateStock(produits, saleProduitRequet);
    }

    private String getStringData(SaleProduitRequet saleProduitRequet, List<ProduitEntity> produits) {
        return "{\"numeroMarchant\":\""+numeroMarchant+"\","
                + "\"numeroCarte\":\""+saleProduitRequet.carteInfoRequest().numeroCarte()+"\","
                + "\"dateExpiration\":\""+saleProduitRequet.carteInfoRequest().dateExpiration()+"\","
                + "\"codeSecurite\":\""+saleProduitRequet.carteInfoRequest().codeSecurite()+"\","
                + "\"montant\":"+getPrixTotal(produits, saleProduitRequet)+"}";
    }

    private void validerProduit(List<ProduitEntity> produits, SaleProduitRequet saleProduitRequet) {
        for(ProduitEntity produit : produits){
            ProduitRequest pr = saleProduitRequet
                    .produitRequests()
                    .stream()
                    .filter(p -> p.id().equals(produit.getId()))
                    .findFirst()
                    .orElseThrow(() -> new ProduitNotFoundException(String.format("Produit %s not found", produit.getLibelle())));
            if (produit.getQuantite() < pr.quantite()) {
                throw new StockInsuffisant("Quantite insuffisante");
            }
        }
    }

    private Double getPrixTotal(List<ProduitEntity> produits, SaleProduitRequet saleProduitRequet){
        double prixTotal = 0.0;
        for(ProduitRequest produitRequest : saleProduitRequet.produitRequests()){
            ProduitEntity produit = produits.stream()
                    .filter(p -> p.getId().equals(produitRequest.id()))
                    .findFirst()
                    .orElseThrow(() -> new ProduitNotFoundException("Produit not found"));
            prixTotal = prixTotal + (produit.getPrix() * produitRequest.quantite());
        }
        return prixTotal;
    }

    private void updateStock(List<ProduitEntity> produits, SaleProduitRequet saleProduitRequet){
        for(ProduitRequest produitRequest : saleProduitRequet.produitRequests()){
            ProduitEntity produit = produits.stream()
                    .filter(p -> p.getId().equals(produitRequest.id()))
                    .findFirst()
                    .orElseThrow(() -> new ProduitNotFoundException("Produit not found"));
            produit.setQuantite(produit.getQuantite() - produitRequest.quantite());
            produitRepository.save(produit);
        }
    }
}
