package com.engine.fakau.servicepayment.request;

public record CreerPaiementRequest(String numeroMarchant, String numeroCarte, String dateExpiration, String codeSecurite, Double montant) {
}
