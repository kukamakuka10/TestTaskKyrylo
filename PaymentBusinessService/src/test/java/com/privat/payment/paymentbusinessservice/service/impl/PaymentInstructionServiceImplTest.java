package com.privat.payment.paymentbusinessservice.service.impl;

import com.privat.payment.paymentbusinessservice.client.PaymentInstructionDaoClient;
import com.privat.payment.paymentbusinessservice.controller.dto.PaymentInstructionRequestDTO;
import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentInstructionServiceImplTest {
    private static final String TRANSFER_ID = "transfer_id";
    private static final String INN = "test_inn";
    private static final String RECIPIENT_OKPO = "test_okpo";

    @Mock
    private PaymentInstructionDaoClient client;

    @InjectMocks
    private PaymentInstructionServiceImpl service;

    @Test
    void create() {
        service.create(PaymentInstructionRequestDTO.builder()
                .paymentAmount(1000L)
                .payerInn(INN)
                .recipientOkpo(RECIPIENT_OKPO)
                .build());

        verify(client).save(PaymentInstructionDTO.builder()
                .paymentAmount(1000L)
                .payerInn(INN)
                .recipientOkpo(RECIPIENT_OKPO)
                .build());
    }

    @Test
    void getById() {
        when(client.getById(anyString()))
                .thenReturn(buildPaymentInstructionDTO());

        assertThat(service.getById(TRANSFER_ID))
                .isEqualTo(buildPaymentInstructionDTO());

        verify(client).getById(TRANSFER_ID);
    }

    @Test
    void getAll() {
        when(client.getAll())
                .thenReturn(List.of(buildPaymentInstructionDTO()));

        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildPaymentInstructionDTO()))
                .build();

        assertThat(service.getAll())
                .isEqualTo(wrapper);

        verify(client).getAll();
    }

    @Test
    void getAllByPayerInn() {
        when(client.getAllByPayerInn(anyString()))
                .thenReturn(List.of(buildPaymentInstructionDTO()));

        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildPaymentInstructionDTO()))
                .build();

        assertThat(service.getAllByPayerInn(INN))
                .isEqualTo(wrapper);

        verify(client).getAllByPayerInn(INN);
    }

    @Test
    void getAllByRecipientOkpo() {
        when(client.getAllByRecipientOkpo(anyString()))
                .thenReturn(List.of(buildPaymentInstructionDTO()));

        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildPaymentInstructionDTO()))
                .build();

        assertThat(service.getAllByRecipientOkpo(RECIPIENT_OKPO))
                .isEqualTo(wrapper);

        verify(client).getAllByRecipientOkpo(RECIPIENT_OKPO);
    }

    private PaymentInstructionDTO buildPaymentInstructionDTO() {
        return PaymentInstructionDTO.builder()
                .id(TRANSFER_ID)
                .paymentAmount(1000L)
                .payerInn(INN)
                .recipientOkpo(RECIPIENT_OKPO)
                .build();
    }
}