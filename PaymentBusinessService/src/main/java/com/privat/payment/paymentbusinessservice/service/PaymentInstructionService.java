package com.privat.payment.paymentbusinessservice.service;

import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import com.privat.payment.paymentbusinessservice.controller.dto.PaymentInstructionRequestDTO;

public interface PaymentInstructionService {
    void create(PaymentInstructionRequestDTO paymentInstructionRequestDTO);
    PaymentInstructionDTO getById(String id);
    WrapperListResponse<PaymentInstructionDTO> getAll();
    WrapperListResponse<PaymentInstructionDTO> getAllByPayerInn(String payerInn);
    WrapperListResponse<PaymentInstructionDTO> getAllByRecipientOkpo(String recipientOkpo);
}
