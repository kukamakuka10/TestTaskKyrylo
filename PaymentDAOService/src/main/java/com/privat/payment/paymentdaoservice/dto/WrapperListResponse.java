package com.privat.payment.paymentdaoservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
public class WrapperListResponse <T> {
    private List<T> data;
}
