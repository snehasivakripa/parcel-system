package com.parcel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcel.domain.ParcelDetails;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(value = AgentController.class)
public class AgentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParcelService parcelService;

    @MockBean
    AgentService agentService;

    @Test
    void getParcelDetailsByAgentId() throws Exception {
        List<ParcelDetails> parcelDetailsList = new ArrayList<>();

        ParcelDetails parcelDetails = new ParcelDetails();
        parcelDetails.setParcelId("123546");
        parcelDetails.setParcelAddress("Kochi");
        parcelDetails.setParcelNumber("AT567DF456");
        parcelDetails.setParcelItem("Bag");
        parcelDetails.setAgentName("Ram");
        parcelDetails.setAgentNumber("975678990");
        parcelDetails.setParcelStatus("Processing");
        parcelDetailsList.add(parcelDetails);
        parcelDetailsList.add(parcelDetails);

        Mockito.when(agentService.getParcelDetailsByAgentId("12345")).thenReturn(parcelDetailsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/parcel/agentDetailsById")
                .param("id","123456")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void getParcelDetailsByAgentIdInternalServerError() throws Exception {
        Mockito.when(agentService.getParcelDetailsByAgentId("123456")).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/parcel/agentDetailsById")
                .param("id","123456")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }


    @Test
    void changeStatus() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ParcelDetails parcelDetails = new ParcelDetails();
        parcelDetails.setParcelId("123546");
        parcelDetails.setParcelAddress("Kochi");
        parcelDetails.setParcelNumber("AT567DF456");
        parcelDetails.setParcelItem("Bag");
        parcelDetails.setAgentName("Ram");
        parcelDetails.setAgentNumber("975678990");
        parcelDetails.setParcelStatus("Processing");
        String json = mapper.writeValueAsString(parcelDetails);
        Mockito.when(agentService.changeStatus(parcelDetails)).thenReturn(parcelDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/parcel/changeStatus")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }


}