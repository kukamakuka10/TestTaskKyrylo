package com.privat.payment.scheduler.client;

import com.privat.payment.scheduler.dto.PaymentInstructionDTO;
import com.privat.payment.scheduler.dto.TransferDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor
public class TransferClient {
    private final RestTemplate restTemplate;
    private final String baseEndpoint = "/payment/transfer";
    public void create(PaymentInstructionDTO paymentInstructionDTO) {
        restTemplate.postForEntity(baseEndpoint, paymentInstructionDTO, PaymentInstructionDTO.class);
    }
    public Boolean checkRecurringTransferNeed(String paymentInstructionId) {
        String url = UriComponentsBuilder.fromUriString(baseEndpoint)
                .path("/check-recurring-transfer")
                .queryParam("paymentInstructionId", paymentInstructionId)
                .build()
                .toString();
        return restTemplate.getForObject(url, Boolean.class);
    }
}
