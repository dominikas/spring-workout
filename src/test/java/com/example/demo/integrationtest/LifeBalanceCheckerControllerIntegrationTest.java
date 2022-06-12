package com.example.demo.integrationtest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LifeBalanceCheckerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenGetRequestToService_isQueryParamArgumentValid_thenReturnTrueOrFalse() throws Exception {
        this.mockMvc.perform(get("/lifebalancecheck/islifebalanced?desiredSelfCareRatio=1.0"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.anyOf(containsString("true"), containsString("false"))));
    }

    @Test
    public void whenGetRequestToServiceisQueryParamArgumentInvalid_thenReturnBadRequestResponse() throws Exception {
        this.mockMvc.perform(get("/lifebalancecheck/islifebalanced?desiredSelfCareRatio=1.1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Constraint validation exception: getLifeBalance.desiredSelfCareRatio: must be less than or equal to 1")));
    }

    @Test
    public void whenGetRequestToServiceisQueryParamArgumentInvalid_thenReturnBadRequestResponse2() throws Exception {
        this.mockMvc.perform(get("/lifebalancecheck/islifebalanced?desiredSelfCareRatio=-0.1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Constraint validation exception: getLifeBalance.desiredSelfCareRatio: must be greater than or equal to 0")));
    }
}