package com.privat.payment.paymentdaoservice.mapper;

import com.privat.payment.paymentdaoservice.controller.dto.TransferDTO;
import com.privat.payment.paymentdaoservice.entities.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PaymentInstructionMapper.class})
public interface TransferMapper {
    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);
    TransferDTO toDto(Transfer transfer);
    Transfer toEntity(TransferDTO transferDTO);

    default List<TransferDTO> toDto(List<Transfer> transfers) {
        if (transfers == null) {
            return Collections.emptyList();
        }
        return transfers.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<Transfer> toEntity(List<TransferDTO> transferDTOs) {
        if (transferDTOs == null) {
            return Collections.emptyList();
        }
        return transferDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
