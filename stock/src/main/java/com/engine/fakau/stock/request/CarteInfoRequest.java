package com.engine.fakau.stock.request;

import java.time.LocalDate;

public record CarteInfoRequest(String numeroCarte,
                               LocalDate dateExpiration,
                               String codeSecurite) {
}
