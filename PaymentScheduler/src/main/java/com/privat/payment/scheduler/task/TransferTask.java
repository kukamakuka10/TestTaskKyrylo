package com.privat.payment.scheduler.task;

import com.privat.payment.scheduler.dto.PaymentInstructionDTO;
import com.privat.payment.scheduler.service.PaymentInstructionService;
import com.privat.payment.scheduler.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TransferTask {

    private final TransferService transferService;
    private final PaymentInstructionService paymentInstructionService;

    @Scheduled(cron = "${spring.scheduler.cron}")
    public void scheduleRecurringTransfers() {
        List<PaymentInstructionDTO> paymentInstructionDTOS = paymentInstructionService.getAll();
        if (!paymentInstructionDTOS.isEmpty()){
            for (PaymentInstructionDTO paymentInstructionDTO : paymentInstructionDTOS) {
                if (transferService.checkRecurringTransferNeed(paymentInstructionDTO.getId())) {
                    transferService.create(paymentInstructionDTO);
                }
            }
        }

    }
}
