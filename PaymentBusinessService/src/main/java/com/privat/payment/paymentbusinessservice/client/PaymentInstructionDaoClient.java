package com.privat.payment.paymentbusinessservice.client;

import com.privat.payment.paymentbusinessservice.client.exception.EntityNotFoundException;
import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.dto.WrapperListResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class PaymentInstructionDaoClient {
    private final RestTemplate restTemplate;
    private final String baseEndpoint = "/payment/instruction";

    public PaymentInstructionDTO getById(String id) {
        String url = baseEndpoint + "/" + id;
        try {
            return restTemplate.getForObject(url, PaymentInstructionDTO.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new EntityNotFoundException(exception.getResponseBodyAsString());
        }
    }

    public void save(PaymentInstructionDTO paymentInstructionDTO) {
        restTemplate.postForEntity(baseEndpoint, paymentInstructionDTO, Void.class);
    }

    public void update(PaymentInstructionDTO paymentInstructionDTO) {
        try {
            restTemplate.put(baseEndpoint, paymentInstructionDTO);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new EntityNotFoundException(exception.getResponseBodyAsString());
        }

    }

    public void delete(String id) {
        String url = UriComponentsBuilder.fromUriString(baseEndpoint)
                .pathSegment(id)
                .build()
                .toUriString();
        try {
            restTemplate.delete(url);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new EntityNotFoundException(exception.getResponseBodyAsString());
        }
    }

    public List<PaymentInstructionDTO> getAll() {
        ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> response = restTemplate
                .exchange(
                        baseEndpoint,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<WrapperListResponse<PaymentInstructionDTO>>() {
                        });

        return Objects.requireNonNull(response.getBody()).getData();
    }

    public List<PaymentInstructionDTO> getAllByPayerInn(String payerInn) {

        String url = UriComponentsBuilder.fromUriString(baseEndpoint + "/payer")
                .queryParam("payerInn", payerInn)
                .build()
                .toString();

        ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> response = restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        return Objects.requireNonNull(response.getBody()).getData();
    }

    public List<PaymentInstructionDTO> getAllByRecipientOkpo(String recipientOkpo) {

        String url = UriComponentsBuilder.fromUriString(baseEndpoint + "/recipient")
                .queryParam("recipientOkpo", recipientOkpo)
                .build()
                .toString();

        ResponseEntity<WrapperListResponse<PaymentInstructionDTO>> response = restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        return Objects.requireNonNull(response.getBody()).getData();
    }
}
