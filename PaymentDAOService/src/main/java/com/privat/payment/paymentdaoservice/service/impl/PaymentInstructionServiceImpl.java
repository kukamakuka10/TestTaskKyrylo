package com.privat.payment.paymentdaoservice.service.impl;

import com.privat.payment.paymentdaoservice.controller.dto.PaymentInstructionDTO;
import com.privat.payment.paymentdaoservice.dao.PaymentInstructionDAO;
import com.privat.payment.paymentdaoservice.dto.WrapperListResponse;
import com.privat.payment.paymentdaoservice.entities.PaymentInstruction;
import com.privat.payment.paymentdaoservice.mapper.PaymentInstructionMapper;
import com.privat.payment.paymentdaoservice.service.PaymentInstructionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentInstructionServiceImpl implements PaymentInstructionService {
    private final PaymentInstructionDAO paymentInstructionDAO;
    private final String PAYMENT_INSTRUCTION_NOT_FOUND = "PaymentInstruction with Id - %s not found";

    @Override
    @Transactional
    public void save(PaymentInstructionDTO paymentInstructionDTO) {
        PaymentInstruction paymentInstruction = PaymentInstructionMapper.INSTANCE.toEntity(paymentInstructionDTO);
        paymentInstructionDAO.save(paymentInstruction);
    }

    @Override
    @Transactional
    public PaymentInstructionDTO update(PaymentInstructionDTO paymentInstructionDTO) {
        String id = paymentInstructionDTO.getId();
        Optional<PaymentInstruction> paymentInstructionOptional = paymentInstructionDAO.findById(id);
        if (paymentInstructionOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format(PAYMENT_INSTRUCTION_NOT_FOUND, id));
        }

        PaymentInstruction paymentInstruction = PaymentInstructionMapper.INSTANCE.toEntity(paymentInstructionDTO);
        paymentInstruction = paymentInstructionDAO.update(paymentInstruction);

        return PaymentInstructionMapper.INSTANCE.toDto(paymentInstruction);
    }

    @Override
    @Transactional
    public void delete(String id) {
        paymentInstructionDAO.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentInstructionDTO findById(String id) {
        return paymentInstructionDAO.findById(id)
                .map(PaymentInstructionMapper.INSTANCE::toDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format(PAYMENT_INSTRUCTION_NOT_FOUND, id)));
    }

    @Override
    @Transactional(readOnly = true)
    public WrapperListResponse<PaymentInstructionDTO> findAll() {
        List<PaymentInstruction> paymentInstructions =  paymentInstructionDAO.findAll();
        List<PaymentInstructionDTO> paymentInstructionDTOS = PaymentInstructionMapper.INSTANCE.toDto(paymentInstructions);
        return WrapperListResponse.<PaymentInstructionDTO>builder()
                .data(paymentInstructionDTOS)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public WrapperListResponse<PaymentInstructionDTO> findAllByPayerInn(String payerInn) {
        List<PaymentInstruction> paymentInstructions =  paymentInstructionDAO.findAllByPayerInn(payerInn);
        List<PaymentInstructionDTO> paymentInstructionDTOS = PaymentInstructionMapper.INSTANCE.toDto(paymentInstructions);
        return WrapperListResponse.<PaymentInstructionDTO>builder()
                    .data(paymentInstructionDTOS)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public WrapperListResponse<PaymentInstructionDTO> findAllByRecipientOkpo(String recipientOkpo) {
        List<PaymentInstruction> paymentInstructions =  paymentInstructionDAO.findAllByRecipientOkpo(recipientOkpo);
        List<PaymentInstructionDTO> paymentInstructionDTOS = PaymentInstructionMapper.INSTANCE.toDto(paymentInstructions);
        return WrapperListResponse.<PaymentInstructionDTO>builder()
                .data(paymentInstructionDTOS)
                .build();
    }
}
