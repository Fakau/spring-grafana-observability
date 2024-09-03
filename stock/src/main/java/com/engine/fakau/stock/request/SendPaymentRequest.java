package com.engine.fakau.stock.request;

import java.time.LocalDate;

public record SendPaymentRequest(String numeroMarchant, String numeroCarte, LocalDate dateExpiration, String codeSecurite, Double montant) {
}
