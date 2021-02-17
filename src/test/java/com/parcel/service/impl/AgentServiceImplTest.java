package com.parcel.service.impl;

import com.parcel.data.AgentRepository;
import com.parcel.data.ParcelRepository;
import com.parcel.domain.AgentDetails;
import com.parcel.domain.LoginResponse;
import com.parcel.domain.ParcelDetails;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AgentServiceImplTest {

    @Mock
    AgentRepository agentRepository;

    @InjectMocks
    AgentServiceImpl agentService;

    @Mock
    ParcelRepository parcelRepository;

    @Mock
    ParcelDetails parcelDetail;

    @BeforeEach
    void  setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void login(){
        List<AgentDetails> agentDetailsArrayList = new ArrayList<>();

        AgentDetails agentDetails = new AgentDetails();
        agentDetails.setAgentId("123546");
        agentDetails.setAgentName("test");
        agentDetails.setAgentPassword("tester");
        agentDetails.setAgentNumber("12");
        agentDetailsArrayList.add(agentDetails);
        Mockito.when(agentRepository.findByAgentName("test")).thenReturn(agentDetailsArrayList);
        LoginResponse loginResponse1=agentService.login("test","test");
        Assert.assertEquals("Failure",loginResponse1.getMessage());
    }

    @Test
    public void addEntry() {
        AgentDetails agentDetails = new AgentDetails();
        agentDetails.setAgentId("123546");
        agentDetails.setAgentName("test");
        agentDetails.setAgentPassword("tester");
        agentDetails.setAgentNumber("12");
        Mockito.when(agentRepository.save(agentDetails)).thenReturn(agentDetails);
        Assert.assertEquals(agentDetails,agentService.addEntry(agentDetails));
    }

    @Test
    public void getParcelDetailsByAgentId() {
        List<ParcelDetails> parcelDetailsList = new ArrayList<>();

        ParcelDetails parcelDetails = new ParcelDetails();
        parcelDetails.setParcelId("123546");
        parcelDetails.setParcelAddress("test");
        parcelDetails.setParcelNumber("tester");
        parcelDetails.setParcelItem("12");
        parcelDetails.setAgentName("4");
        parcelDetails.setAgentNumber("1234654");
        parcelDetails.setParcelStatus("Processing");
        parcelDetailsList.add(parcelDetails);
        Mockito.when(parcelRepository.findByAgentId("test")).thenReturn(parcelDetailsList);
        Assert.assertEquals(parcelDetailsList,agentService.getParcelDetailsByAgentId("test"));

    }

    @Test
    public void getAllAgentDetails() {
        List<AgentDetails> agentDetailsArrayList = new ArrayList<>();
        AgentDetails agentDetails = new AgentDetails();
        agentDetails.setAgentId("123546");
        agentDetails.setAgentName("test");
        agentDetails.setAgentPassword("tester");
        agentDetails.setAgentNumber("12");
        agentDetailsArrayList.add(agentDetails);
        Mockito.when(agentRepository.findAll()).thenReturn(agentDetailsArrayList);
        Assert.assertEquals(agentDetailsArrayList,agentService.getAllAgentDetails());

    }

}