package com.privat.payment.scheduler.service.impl;

import com.privat.payment.scheduler.client.PaymentInstructionClient;
import com.privat.payment.scheduler.dto.PaymentInstructionDTO;
import com.privat.payment.scheduler.service.PaymentInstructionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentInstructionServiceImpl implements PaymentInstructionService {
    private final PaymentInstructionClient client;
    @Override
    public List<PaymentInstructionDTO> getAll() {
        return client.getAll();
    }
}
