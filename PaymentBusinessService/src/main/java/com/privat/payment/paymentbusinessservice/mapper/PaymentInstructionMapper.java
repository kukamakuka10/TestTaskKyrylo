package com.privat.payment.paymentbusinessservice.mapper;

import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.controller.dto.PaymentInstructionRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PaymentInstructionMapper {
    PaymentInstructionMapper INSTANCE = Mappers.getMapper(PaymentInstructionMapper.class);

    PaymentInstructionDTO requestToDto(PaymentInstructionRequestDTO requestDTO);

}
