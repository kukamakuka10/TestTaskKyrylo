package com.privat.payment.paymentbusinessservice.service.impl;

import com.privat.payment.paymentbusinessservice.client.TransferDaoClient;
import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.TransactionStatus;
import com.privat.payment.paymentbusinessservice.dto.TransferDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {
    private static final String ID = "test_id";
    private static final String INN = "test_inn";
    private static final String RECIPIENT_OKPO = "test_okpo";

    @Mock
    private TransferDaoClient client;
    @InjectMocks
    private TransferServiceImpl service;

    @Test
    void create() {
        service.create(PaymentInstructionDTO.builder()
                .id(ID)
                .paymentAmount(1000L)
                .payerInn(INN)
                .recipientOkpo(RECIPIENT_OKPO)
                .build());

        verify(client).save(buildTransferDTO());
    }

    @Test
    void cancel() {
        when(client.getById(anyString()))
                .thenReturn(buildTransferDTO());

        service.cancel(ID);

        TransferDTO transferForUpdate = buildTransferDTO();
        transferForUpdate.setStatus(TransactionStatus.S);

        verify(client).getById(ID);
        verify(client).update(transferForUpdate);
    }

    @Test
    void getHistory() {
        when(client.getAllByPaymentInstructionId(anyString()))
                .thenReturn(List.of(buildTransferDTO()));

        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildTransferDTO()))
                .build();

        assertThat(service.getHistory(ID))
                .isEqualTo(wrapper);

        verify(client).getAllByPaymentInstructionId(ID);
    }

    private TransferDTO buildTransferDTO() {
        return TransferDTO.builder()
                .id(ID)
                .status(TransactionStatus.A)
                .transactionAmount(1000L)
                .paymentInstruction(PaymentInstructionDTO.builder()
                        .id(ID)
                        .paymentAmount(1000L)
                        .payerInn(INN)
                        .recipientOkpo(RECIPIENT_OKPO)
                        .build())
                .build();
    }
}