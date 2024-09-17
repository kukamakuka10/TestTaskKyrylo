package com.privat.payment.paymentbusinessservice.client;

import com.privat.payment.paymentbusinessservice.client.exception.EntityNotFoundException;
import com.privat.payment.paymentbusinessservice.dto.TransferDTO;
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
public class TransferDaoClient {
    private final RestTemplate restTemplate;
    private final String baseEndpoint = "/payment/transfer";

    public TransferDTO getById(String id) {
        String url = UriComponentsBuilder.fromUriString(baseEndpoint)
                .pathSegment(id)
                .build()
                .toUriString();
        return restTemplate.getForObject(url, TransferDTO.class);
    }

    public void save(TransferDTO transferDTO) {
        restTemplate.postForEntity(baseEndpoint, transferDTO, Void.class);
    }

    public void update(TransferDTO transferDTO) {
        String url = UriComponentsBuilder.fromUriString(baseEndpoint)
                .pathSegment(transferDTO.getId())
                .build()
                .toUriString();
        try {
            restTemplate.put(url, transferDTO);
        }catch (HttpClientErrorException.NotFound exception){
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
        }catch (HttpClientErrorException.NotFound exception){
            throw new EntityNotFoundException(exception.getResponseBodyAsString());
        }

    }

    public List<TransferDTO> getAllByPaymentInstructionId(String id) {
        String url = UriComponentsBuilder.fromUriString(baseEndpoint)
                .queryParam("paymentInstructionId", id)
                .build()
                .toString();

        ResponseEntity<WrapperListResponse<TransferDTO>> response = restTemplate
                .exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });
        return Objects.requireNonNull(response.getBody()).getData();
    }
}
