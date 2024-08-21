package com.engine.fakau.servicepayment.service.dto;

public record CreerPaiementDTO(String numeroMarchant, String numeroCarte, String dateExpiration, String codeSecurite, Double montant) {
}
