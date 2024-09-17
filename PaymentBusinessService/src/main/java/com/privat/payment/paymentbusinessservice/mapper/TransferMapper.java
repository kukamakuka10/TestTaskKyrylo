package com.privat.payment.paymentbusinessservice.mapper;

import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.TransferDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferMapper {
    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);


    @Mapping(target = "paymentInstruction", expression = "java(getPaymentInstruction(paymentInstructionDTO))")
    @Mapping(source = "paymentAmount", target = "transactionAmount")
    @Mapping(target = "status", constant = "A")
    TransferDTO paymentToTransfer(PaymentInstructionDTO paymentInstructionDTO);

    default PaymentInstructionDTO getPaymentInstruction(PaymentInstructionDTO paymentInstructionDTO) {
        return paymentInstructionDTO;
    }

}
