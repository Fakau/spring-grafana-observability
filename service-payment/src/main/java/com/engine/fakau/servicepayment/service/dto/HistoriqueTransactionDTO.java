package com.engine.fakau.servicepayment.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class HistoriqueTransactionDTO implements Serializable {
    private Long id;
    private String numeroTransaction;
    private LocalDateTime dateTransaction;
    private BigDecimal montant;
    private CarteCreditDTO carteCredit;
    private CarteCreditDTO compteMarchand;

}
