package com.privat.payment.scheduler.service.impl;

import com.privat.payment.scheduler.client.TransferClient;
import com.privat.payment.scheduler.dto.PaymentInstructionDTO;
import com.privat.payment.scheduler.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferClient client;
    @Override
    public Boolean checkRecurringTransferNeed(String paymentInstructionId) {
        return client.checkRecurringTransferNeed(paymentInstructionId);
    }

    @Override
    public void create(PaymentInstructionDTO paymentInstructionDTO) {
        client.create(paymentInstructionDTO);
    }
}
