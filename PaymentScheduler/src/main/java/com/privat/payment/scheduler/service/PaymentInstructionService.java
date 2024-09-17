package com.privat.payment.scheduler.service;

import com.privat.payment.scheduler.dto.PaymentInstructionDTO;

import java.util.List;

public interface PaymentInstructionService {
    List<PaymentInstructionDTO> getAll();
}
