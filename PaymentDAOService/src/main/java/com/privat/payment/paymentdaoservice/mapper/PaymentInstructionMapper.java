package com.privat.payment.paymentdaoservice.mapper;

import com.privat.payment.paymentdaoservice.controller.dto.PaymentInstructionDTO;
import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PaymentInstructionMapper {
    PaymentInstructionMapper INSTANCE = Mappers.getMapper(PaymentInstructionMapper.class);

    PaymentInstructionDTO toDto(PaymentInstruction paymentInstruction);

    PaymentInstruction toEntity(PaymentInstructionDTO paymentInstructionDTO);

    default List<PaymentInstructionDTO> toDto(List<PaymentInstruction> paymentInstructions) {
        if (paymentInstructions == null) {
            return Collections.emptyList();
        }
        return paymentInstructions.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<PaymentInstruction> toEntity(List<PaymentInstructionDTO> paymentInstructionDTOs) {
        if (paymentInstructionDTOs == null) {
            return Collections.emptyList();
        }
        return paymentInstructionDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
