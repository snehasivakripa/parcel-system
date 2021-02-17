package com.parcel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parcel.domain.AgentDetails;
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
@WebMvcTest(value = AdminController.class)
public class AdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParcelService parcelService;

    @MockBean
    AgentService agentService;

    @Test
    void getAllParcelDetails() throws Exception {
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

        Mockito.when(parcelService.getAllParcelDetails()).thenReturn(parcelDetailsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/parcel/parcelDetails").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void getAllParcelDetailsInternalServerError() throws Exception {
        Mockito.when(parcelService.getAllParcelDetails()).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/parcel/parcelDetails").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }

    @Test
    void getAllAgentDetails() throws Exception {
        List<AgentDetails> agentDetailsArrayList = new ArrayList<>();

        AgentDetails agentDetails = new AgentDetails();
        agentDetails.setAgentId("edfgg4578wety");
        agentDetails.setAgentName("Shyam");
        agentDetails.setAgentPassword("Ter124");
        agentDetails.setAgentNumber("875678904");
        agentDetailsArrayList.add(agentDetails);

        Mockito.when(agentService.getAllAgentDetails()).thenReturn(agentDetailsArrayList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/parcel/agentDetails").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    void getAllAgentDetailsInternalServerError() throws Exception {
        Mockito.when(agentService.getAllAgentDetails()).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/parcel/agentDetails").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }

    @Test
    void addAgent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AgentDetails agentDetails = new AgentDetails();
        agentDetails.setAgentId("edfgg4578wety");
        agentDetails.setAgentName("Shyam");
        agentDetails.setAgentPassword("Ter124");
        agentDetails.setAgentNumber("875678904");
        String json = mapper.writeValueAsString(agentDetails);
        Mockito.when(agentService.addEntry(agentDetails)).thenReturn(agentDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/parcel/addDeliveryEntry")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    void aaddAgentInternalError() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        AgentDetails agentDetails = new AgentDetails();
        String json = mapper.writeValueAsString(agentDetails);
        Mockito.when(agentService.addEntry(agentDetails)).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/parcel/addAgent")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }
    @Test
    void addDeliveryEntry() throws Exception {
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
        Mockito.when(parcelService.addEntry(parcelDetails)).thenReturn(parcelDetails);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/parcel/addDeliveryEntry")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
    }

    @Test
    void addDeliveryEntryInternalError() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ParcelDetails parcelDetails = new ParcelDetails();
        String json = mapper.writeValueAsString(parcelDetails);
        Mockito.when(parcelService.addEntry(parcelDetails)).thenThrow(new RuntimeException());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/parcel/addDeliveryEntry")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
    }

}