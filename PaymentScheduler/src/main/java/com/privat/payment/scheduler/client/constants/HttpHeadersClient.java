package com.privat.payment.scheduler.client.constants;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Getter
public class HttpHeadersClient {
    private HttpHeaders headers;

    @PostConstruct
    void init() {
        headers = new HttpHeaders();
        headers.add(HttpHeaders.USER_AGENT, "Application");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
}
