package com.example.demo.integrationtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LifeBalanceCheckerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGetRequestToServiceHasValidArgument_thenReturnTrueOrFalse() {
        URI requestUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .path("/lifebalancecheck/islifebalanced")
                .port(port)
                .queryParam("desiredSelfCareRatio", "0.7")
                .build()
                .toUri();
        String response = restTemplate.getForObject(requestUri, String.class);
        Assertions.assertThat(response).containsAnyOf("true", "false");
    }

    @Test
    public void whenGetRequestToServiceHasInvalidQueryParamValue_thenReturnBadRequestResponse() {
        URI requestUri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .path("/lifebalancecheck/islifebalanced")
                .port(port)
                .queryParam("desiredSelfCareRatio", "1.7")
                .build()
                .toUri();
        String response = restTemplate.getForObject(requestUri, String.class);
        Assertions.assertThat(response).isEqualTo("Constraint validation exception: getLifeBalance.desiredSelfCareRatio: must be less than or equal to 1");
    }
}