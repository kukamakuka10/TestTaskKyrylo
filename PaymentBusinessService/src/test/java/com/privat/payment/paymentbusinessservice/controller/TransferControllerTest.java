package com.privat.payment.paymentbusinessservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.privat.payment.paymentbusinessservice.dto.PaymentInstructionDTO;
import com.privat.payment.paymentbusinessservice.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"spring.service.url=test", "server.port=8080"})
@AutoConfigureMockMvc
class TransferControllerTest {
    private static final String CREATE_URL = "/payment/transfer";
    private static final String CHECK_URL = CREATE_URL.concat("/check-recurring-transfer");
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransferService transferService;
    private static final ObjectWriter OBJECT_WRITER = new ObjectMapper().writer();

    @Test
    void create() throws Exception {
        mockMvc.perform(post(CREATE_URL)
                        .content(OBJECT_WRITER.writeValueAsString(PaymentInstructionDTO.builder()
                                .id("test_id")
                                .payerInn("test_inn")
                                .period(100L)
                                .payerCardNumber("123123123123")
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(transferService).create(PaymentInstructionDTO.builder()
                .id("test_id")
                .payerInn("test_inn")
                .period(100L)
                .payerCardNumber("123123123123")
                .build()
        );
    }

    @Test
    void check() throws Exception {
        when(transferService.checkRecurringTransferNeed(any()))
                .thenReturn(Boolean.TRUE);

        mockMvc.perform(get(CHECK_URL)
                        .queryParam("paymentInstructionId", "test_id"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(transferService).checkRecurringTransferNeed("test_id");
    }

}