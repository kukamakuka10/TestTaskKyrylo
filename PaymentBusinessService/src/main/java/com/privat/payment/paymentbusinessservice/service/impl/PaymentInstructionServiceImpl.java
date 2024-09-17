package com.privat.payment.paymentbusinessservice.service.impl;

import com.privat.payment.paymentbusinessservice.client.PaymentInstructionDaoClient;
import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import com.privat.payment.paymentbusinessservice.controller.dto.PaymentInstructionRequestDTO;
import com.privat.payment.paymentbusinessservice.mapper.PaymentInstructionMapper;
import com.privat.payment.paymentbusinessservice.service.PaymentInstructionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentInstructionServiceImpl implements PaymentInstructionService {
    private final PaymentInstructionDaoClient client;

    @Override
    public void create(PaymentInstructionRequestDTO paymentInstructionRequestDTO) {
        PaymentInstructionDTO paymentInstructionDTO = PaymentInstructionMapper
                .INSTANCE
                .requestToDto(paymentInstructionRequestDTO);
        client.save(paymentInstructionDTO);
    }

    @Override
    public PaymentInstructionDTO getById(String id) {
        return client.getById(id);
    }

    @Override
    public WrapperListResponse<PaymentInstructionDTO> getAll() {
        List<PaymentInstructionDTO> paymentInstructionDTOS = client.getAll();
        return WrapperListResponse.<PaymentInstructionDTO>builder()
                .data(paymentInstructionDTOS)
                .build();
    }

    @Override
    public WrapperListResponse<PaymentInstructionDTO> getAllByPayerInn(String payerInn) {
        List<PaymentInstructionDTO> paymentInstructionDTOS = client.getAllByPayerInn(payerInn);
        return WrapperListResponse.<PaymentInstructionDTO>builder()
                .data(paymentInstructionDTOS)
                .build();
    }

    @Override
    public WrapperListResponse<PaymentInstructionDTO> getAllByRecipientOkpo(String recipientOkpo) {
        List<PaymentInstructionDTO> paymentInstructionDTOS = client.getAllByRecipientOkpo(recipientOkpo);
        return WrapperListResponse.<PaymentInstructionDTO>builder()
                .data(paymentInstructionDTOS)
                .build();
    }
}
