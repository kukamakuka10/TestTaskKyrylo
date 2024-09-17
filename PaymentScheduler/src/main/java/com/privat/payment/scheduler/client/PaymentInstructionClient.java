package com.privat.payment.scheduler.client;

import com.privat.payment.scheduler.dto.PaymentInstructionDTO;

import com.privat.payment.scheduler.dto.WrapperListResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class PaymentInstructionClient {
    private final RestTemplate restTemplate;
    private final String baseEndpoint = "/payment/instruction";
    public List<PaymentInstructionDTO> getAll() {
        ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> response = restTemplate
                .exchange(
                        baseEndpoint,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        return Objects.requireNonNull(response.getBody()).getData();
    }
}
