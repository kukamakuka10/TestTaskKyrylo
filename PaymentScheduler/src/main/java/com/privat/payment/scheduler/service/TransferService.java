package com.privat.payment.scheduler.service;

import com.privat.payment.scheduler.dto.PaymentInstructionDTO;

public interface TransferService {
    Boolean checkRecurringTransferNeed(String paymentInstructionId);

    void create(PaymentInstructionDTO paymentInstructionDTO);
}
