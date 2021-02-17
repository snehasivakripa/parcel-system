package com.parcel.controller;

import com.parcel.domain.LoginResponse;
import com.parcel.service.AgentService;
import com.parcel.service.ParcelService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParcelService parcelService;

    @MockBean
    AgentService agentService;

    @Test
    void login() throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUid("123546");
        loginResponse.setMessage("test");
        loginResponse.setRole("tester");

        Mockito.when(agentService.login("test","test")).thenReturn(loginResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/parcel/login")
                .param("agentName","test")
                .param("agentPassword","test")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }



}