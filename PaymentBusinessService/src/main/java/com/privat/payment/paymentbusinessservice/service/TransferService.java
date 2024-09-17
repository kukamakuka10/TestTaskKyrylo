package com.privat.payment.paymentbusinessservice.service;

import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.TransferDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;

public interface TransferService {
    void create(PaymentInstructionDTO paymentInstructionDTO);
    Boolean checkRecurringTransferNeed(String paymentInstructionId);
    void cancel(String transferId);
    WrapperListResponse<TransferDTO> getHistory(String paymentInstructionId);
}
