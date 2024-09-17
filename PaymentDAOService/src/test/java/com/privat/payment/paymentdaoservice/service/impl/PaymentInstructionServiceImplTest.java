package com.privat.payment.paymentdaoservice.service.impl;

import com.privat.payment.paymentdaoservice.controller.dto.PaymentInstructionDTO;
import com.privat.payment.paymentdaoservice.dao.PaymentInstructionDAO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;
import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentInstructionServiceImplTest {
    private static final String ID = "transfer_id";
    private static final String INN = "test_inn";
    private static final String RECIPIENT_OKPO = "test_okpo";
    @Mock
    private PaymentInstructionDAO paymentInstructionDAO;
    @InjectMocks
    private PaymentInstructionServiceImpl service;

    @Test
    void save() {
        service.save(buildPaymentInstructionDTO());

        verify(paymentInstructionDAO).save(buildPaymentInstruction());
    }

    @Test
    void update() {
        when(paymentInstructionDAO.findById(any()))
                .thenReturn(Optional.of(buildPaymentInstruction()));
        when(paymentInstructionDAO.update(any()))
                .thenReturn(buildPaymentInstruction());

        assertThat(service.update(buildPaymentInstructionDTO()))
                .isEqualTo(buildPaymentInstructionDTO());

        verify(paymentInstructionDAO).findById(ID);
        verify(paymentInstructionDAO).update(buildPaymentInstruction());
    }

    @Test
    void delete() {
        when(paymentInstructionDAO.delete(anyString()))
                .thenReturn(true);

        service.delete(ID);

        verify(paymentInstructionDAO).delete(ID);
    }

    @Test
    void findAll() {
        when(paymentInstructionDAO.findAll())
                .thenReturn(List.of(buildPaymentInstruction()));
        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildPaymentInstructionDTO()))
                .build();
        assertThat(service.findAll())
                .isEqualTo(wrapper);

        verify(paymentInstructionDAO).findAll();
    }

    @Test
    void findById() {
        when(paymentInstructionDAO.findById(anyString()))
                .thenReturn(Optional.of(buildPaymentInstruction()));
        assertThat(service.findById(ID))
                .isEqualTo(buildPaymentInstructionDTO());

        verify(paymentInstructionDAO).findById(ID);
    }

    @Test
    void findAllByPayerInn() {
        when(paymentInstructionDAO.findAllByPayerInn(anyString()))
                .thenReturn(List.of(buildPaymentInstruction()));

        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildPaymentInstructionDTO()))
                .build();

        assertThat(service.findAllByPayerInn(INN))
                .isEqualTo(wrapper);

        verify(paymentInstructionDAO).findAllByPayerInn(INN);
    }

    @Test
    void findAllByRecipientOkpo() {
        when(paymentInstructionDAO.findAllByRecipientOkpo(anyString()))
                .thenReturn(List.of(buildPaymentInstruction()));

        WrapperListResponse wrapper = WrapperListResponse.builder()
                .data(List.of(buildPaymentInstructionDTO()))
                .build();

        assertThat(service.findAllByRecipientOkpo(RECIPIENT_OKPO))
                .isEqualTo(wrapper);

        verify(paymentInstructionDAO).findAllByRecipientOkpo(RECIPIENT_OKPO);
    }

    private PaymentInstruction buildPaymentInstruction() {
        PaymentInstruction paymentInstruction = new PaymentInstruction();
        paymentInstruction.setId(ID);
        paymentInstruction.setPaymentAmount(1000L);
        paymentInstruction.setPayerInn(INN);
        paymentInstruction.setRecipientOkpo(RECIPIENT_OKPO);
        return paymentInstruction;
    }

    private PaymentInstructionDTO buildPaymentInstructionDTO() {
        PaymentInstructionDTO paymentInstruction = new PaymentInstructionDTO();
        paymentInstruction.setId(ID);
        paymentInstruction.setPaymentAmount(1000L);
        paymentInstruction.setPayerInn(INN);
        paymentInstruction.setRecipientOkpo(RECIPIENT_OKPO);
        return paymentInstruction;
    }
}