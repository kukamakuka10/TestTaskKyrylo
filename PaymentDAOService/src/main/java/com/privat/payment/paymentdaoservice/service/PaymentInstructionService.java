package com.privat.payment.paymentdaoservice.service;

import com.privat.payment.paymentdaoservice.controller.dto.PaymentInstructionDTO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;


public interface PaymentInstructionService {
    void save(PaymentInstructionDTO paymentInstruction);
    PaymentInstructionDTO update(PaymentInstructionDTO paymentInstruction);
    void delete(String id);
    WrapperListResponse<PaymentInstructionDTO> findAll();
    PaymentInstructionDTO findById(String id);
    WrapperListResponse<PaymentInstructionDTO> findAllByPayerInn(String payerInn);
    WrapperListResponse<PaymentInstructionDTO> findAllByRecipientOkpo(String recipientOkpo);
}
