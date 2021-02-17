package com.parcel.service.impl;

import com.parcel.data.AgentRepository;
import com.parcel.data.ParcelRepository;
import com.parcel.domain.AgentDetails;
import com.parcel.domain.LoginResponse;
import com.parcel.domain.ParcelDetails;
import com.parcel.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AgentServiceImpl implements AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    ParcelRepository parcelRepository;

    @Override
    public void deleteAgent(String id) {
        agentRepository.deleteById(id);
    }

    @Override
    public LoginResponse login(String userName, String passWord) {
        LoginResponse loginResponse=new LoginResponse();
        List<AgentDetails> agentDetails=agentRepository.findByAgentName(userName);
        for(AgentDetails agentDetails1:agentDetails) {
            if (agentDetails1.getAgentName().equals(userName) && agentDetails1.getAgentPassword().equals(passWord)){
                loginResponse.setMessage("Success");
                loginResponse.setRole(agentDetails1.getRole());
                loginResponse.setUid(agentDetails1.getAgentId());
                return loginResponse;
            }
        }
        loginResponse.setMessage("Failure");
        return loginResponse;
    }

    @Override
    public AgentDetails addEntry(AgentDetails agentDetails) {
        agentDetails.setAgentId(UUID.randomUUID().toString());
        return agentRepository.save(agentDetails);
    }

    @Override
    public List<ParcelDetails> getParcelDetailsByAgentId(String agentId) {
       List<ParcelDetails> parcelDetails=parcelRepository.findByAgentId(agentId);
       return parcelDetails.parallelStream().filter(s-> s.getParcelStatus().equals("Processing")).collect(Collectors.toList());
    }

    @Override
    public ParcelDetails changeStatus(ParcelDetails parcelDetail) {
        ParcelDetails parcelDetails=parcelRepository.findByParcelId(parcelDetail.getParcelId());
        ParcelDetails parcelDetails1;
        parcelDetails1=parcelDetails;
        parcelDetails1.setParcelStatus(parcelDetail.getParcelStatus());
        return parcelRepository.save(parcelDetails1);

    }
    @Override
    public List<AgentDetails> getAllAgentDetails() {
        return agentRepository.findAll();
    }
}
