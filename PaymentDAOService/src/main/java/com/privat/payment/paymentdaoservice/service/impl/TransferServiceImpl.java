package com.privat.payment.paymentdaoservice.service.impl;

import com.privat.payment.paymentdaoservice.controller.dto.TransferDTO;
import com.privat.payment.paymentdaoservice.dao.TransferDAO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;
import com.privat.payment.paymentdaoservice.entities.Transfer;
import com.privat.payment.paymentdaoservice.mapper.TransferMapper;
import com.privat.payment.paymentdaoservice.service.TransferService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final TransferDAO transferDAO;
    private final String TRANSFER_NOT_FOUND = "Transfer with Id - %s not found";

    @Override
    @Transactional
    public void save(TransferDTO transferDTO) {
        Transfer transfer = TransferMapper.INSTANCE.toEntity(transferDTO);
        transferDAO.save(transfer);
    }

    @Override
    @Transactional
    public TransferDTO update(TransferDTO transferDTO) {
        String id = transferDTO.getId();
        Optional<Transfer> transferOptional = transferDAO.findById(id);
        if (transferOptional.isEmpty()){
            throw new EntityNotFoundException(String.format(TRANSFER_NOT_FOUND, id));
        }

        Transfer transfer = TransferMapper.INSTANCE.toEntity(transferDTO);
        transfer =  transferDAO.update(transfer);

        return TransferMapper.INSTANCE.toDto(transfer);
    }

    @Override
    @Transactional
    public void delete(String id) {
        transferDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public TransferDTO findById(String id) {
        return transferDAO.findById(id)
                .map(TransferMapper.INSTANCE::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format(TRANSFER_NOT_FOUND, id)));
    }

    @Override
    @Transactional(readOnly = true)
    public WrapperListResponse<TransferDTO> findAllByPaymentInstructionId(String id) {
        List<Transfer> transfers =  transferDAO.findAllByPaymentInstructionId(id);
        List<TransferDTO> transferDTOS = TransferMapper.INSTANCE.toDto(transfers);
        return WrapperListResponse.<TransferDTO>builder()
                .data(transferDTOS)
                .build();
    }
}
