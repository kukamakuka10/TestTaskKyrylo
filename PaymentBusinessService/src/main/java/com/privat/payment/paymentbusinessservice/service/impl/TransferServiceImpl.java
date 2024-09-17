package com.privat.payment.paymentbusinessservice.service.impl;

import com.privat.payment.paymentbusinessservice.client.TransferDaoClient;
import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.TransactionStatus;
import com.privat.payment.paymentbusinessservice.dto.TransferDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import com.privat.payment.paymentbusinessservice.mapper.TransferMapper;
import com.privat.payment.paymentbusinessservice.service.PaymentInstructionService;
import com.privat.payment.paymentbusinessservice.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferDaoClient client;
    private final PaymentInstructionService paymentInstructionService;

    @Override
    public void create(PaymentInstructionDTO paymentInstructionDTO) {
        TransferDTO transferDTO = TransferMapper.INSTANCE.paymentToTransfer(paymentInstructionDTO);
        client.save(transferDTO);
    }

    @Override
    public Boolean checkRecurringTransferNeed(String paymentInstructionId) {

        PaymentInstructionDTO paymentInstructionDTO = paymentInstructionService.getById(paymentInstructionId);
        List<TransferDTO> transferDTOS = client.getAllByPaymentInstructionId(paymentInstructionId);

        Optional<TransferDTO> lastTransferOpt = transferDTOS.stream()
                .filter(transfer -> transfer.getStatus() == TransactionStatus.A)
                .max(Comparator.comparing(TransferDTO::getTransactionDateTime));

        if (lastTransferOpt.isEmpty()) {
            return true;
        }

        TransferDTO lastTransfer = lastTransferOpt.get();

        LocalDateTime nextPaymentDueDateTime = lastTransfer.getTransactionDateTime()
                .plusSeconds(paymentInstructionDTO.getPeriod());

        LocalDateTime currentDateTime = LocalDateTime.now();

        return currentDateTime.isAfter(nextPaymentDueDateTime);
    }


    @Override
    public void cancel(String transferId) {
        TransferDTO transferDTO = client.getById(transferId);
        transferDTO.setStatus(TransactionStatus.S);
        client.update(transferDTO);
    }

    @Override
    public WrapperListResponse<TransferDTO> getHistory(String paymentInstructionId) {
        List<TransferDTO>transferDTOS = client.getAllByPaymentInstructionId(paymentInstructionId);
        return WrapperListResponse.<TransferDTO>builder()
                .data(transferDTOS)
                .build();
    }
}
