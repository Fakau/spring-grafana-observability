package com.engine.fakau.servicepayment.service.mapper;

import com.engine.fakau.servicepayment.entities.HistoriqueTransactionEntity;
import com.engine.fakau.servicepayment.service.dto.HistoriqueTransactionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoriqueTransactionMapper {
    HistoriqueTransactionDTO toDto(HistoriqueTransactionEntity entity);
    HistoriqueTransactionEntity toEntity(HistoriqueTransactionDTO dto);
}
