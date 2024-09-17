package com.privat.payment.paymentdaoservice.service.impl;

import com.privat.payment.paymentdaoservice.controller.dto.PaymentInstructionDTO;
import com.privat.payment.paymentdaoservice.controller.dto.TransferDTO;
import com.privat.payment.paymentdaoservice.dao.TransferDAO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;
import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;
import com.privat.payment.paymentdaoservice.entities.Transfer;
import com.privat.payment.paymentdaoservice.entities.enums.TransactionStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferServiceImplTest {
    private static final String ID = "test_id";
    private static final String INN = "test_inn";
    private static final String RECIPIENT_OKPO = "test_okpo";

    @Mock
    private TransferDAO dao;
    @InjectMocks
    private TransferServiceImpl service;

    @Test
    void save() {
        service.save(buildTransferDTO());

        verify(dao).save(buildTransfer());
    }

    @Test
    void update() {
        when(dao.findById(any()))
                .thenReturn(Optional.of(buildTransfer()));

        when(dao.update(any()))
                .thenReturn(buildTransfer());

        assertThat(service.update(buildTransferDTO()))
                .isEqualTo(buildTransferDTO());

        verify(dao).update(buildTransfer());
        verify(dao).findById(ID);
    }

    @Test
    void delete() {
        when(dao.delete(anyString()))
                .thenReturn(true);

        service.delete(ID);

        verify(dao).delete(ID);
    }

    @Test
    void delete_notFound() {
        when(dao.delete(anyString()))
                .thenReturn(false);

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> service.delete(ID))
                .matches(e -> e.getMessage().equals("Transfer with Id - test_id not found"));

        verify(dao).delete(ID);
    }

    @Test
    void findById() {
        when(dao.findById(any()))
                .thenReturn(Optional.of(buildTransfer()));

        assertThat(service.findById(ID))
                .isEqualTo(buildTransferDTO());

        verify(dao).findById(ID);
    }

    @Test
    void findAllByPaymentInstructionId() {
        when(dao.findAllByPaymentInstructionId(any()))
                .thenReturn(List.of(buildTransfer()));

        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildTransferDTO()))
                .build();

        assertThat(service.findAllByPaymentInstructionId(ID))
                .isEqualTo(wrapper);

        verify(dao).findAllByPaymentInstructionId(ID);
    }

    private Transfer buildTransfer() {
        PaymentInstruction paymentInstruction = new PaymentInstruction();
        paymentInstruction.setId(ID);
        paymentInstruction.setPaymentAmount(1000L);
        paymentInstruction.setPayerInn(INN);
        paymentInstruction.setRecipientOkpo(RECIPIENT_OKPO);

        Transfer transfer = new Transfer();
        transfer.setId(ID);
        transfer.setStatus(TransactionStatus.A);
        transfer.setTransactionAmount(1000L);
        transfer.setPaymentInstruction(paymentInstruction);
        return transfer;
    }

    private TransferDTO buildTransferDTO() {
        PaymentInstructionDTO paymentInstruction = new PaymentInstructionDTO();
        paymentInstruction.setId(ID);
        paymentInstruction.setPaymentAmount(1000L);
        paymentInstruction.setPayerInn(INN);
        paymentInstruction.setRecipientOkpo(RECIPIENT_OKPO);

        TransferDTO transfer = new TransferDTO();
        transfer.setId(ID);
        transfer.setStatus(TransactionStatus.A);
        transfer.setTransactionAmount(1000L);
        transfer.setPaymentInstruction(paymentInstruction);
        return transfer;
    }
}