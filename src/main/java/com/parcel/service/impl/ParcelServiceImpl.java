package com.parcel.service.impl;

import com.parcel.data.AgentRepository;
import com.parcel.data.ParcelRepository;
import com.parcel.domain.ParcelDetails;
import com.parcel.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ParcelServiceImpl implements ParcelService {

    @Autowired
    ParcelRepository parcelRepository;
    @Autowired
    AgentRepository agentRepository;

    @Override
    public List<ParcelDetails> getAllParcelDetails() {
       return parcelRepository.findAll();
    }

    @Override
    public ParcelDetails addEntry(ParcelDetails parcelDetails) {
        parcelDetails.setParcelId(UUID.randomUUID().toString());
        return parcelRepository.save(parcelDetails);
    }

    @Override
    public void deleteParcelEntries(String id) {
        parcelRepository.deleteById(id);
    }


}
