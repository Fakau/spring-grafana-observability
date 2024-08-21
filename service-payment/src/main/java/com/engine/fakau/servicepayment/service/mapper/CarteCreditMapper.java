package com.engine.fakau.servicepayment.service.mapper;

import com.engine.fakau.servicepayment.entities.CarteCreditEntity;
import com.engine.fakau.servicepayment.service.dto.CarteCreditDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarteCreditMapper {
    CarteCreditDTO toDto(CarteCreditEntity entity);
    CarteCreditEntity toEntity(CarteCreditDTO dto);
}
