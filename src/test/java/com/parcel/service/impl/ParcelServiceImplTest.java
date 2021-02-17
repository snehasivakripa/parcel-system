package com.parcel.service.impl;

import com.parcel.data.AgentRepository;
import com.parcel.data.ParcelRepository;
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
public class ParcelServiceImplTest {

    @Mock
    AgentRepository agentRepository;

    @InjectMocks
    ParcelServiceImpl parcelService;

    @Mock
    ParcelRepository parcelRepository;

    @Mock
    ParcelDetails parcelDetail;

    @BeforeEach
    void  setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllParcelDetails(){
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
        Mockito.when(parcelRepository.findAll()).thenReturn(parcelDetailsList);
        Assert.assertEquals(parcelDetailsList,parcelService.getAllParcelDetails());
    }

    @Test
    public void addEntry() {
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
        Mockito.when(parcelRepository.save(parcelDetails)).thenReturn(parcelDetails);
        Assert.assertEquals(parcelDetails,parcelService.addEntry(parcelDetails));
    }



}