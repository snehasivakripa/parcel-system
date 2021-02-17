package com.parcel.service;

import com.parcel.domain.AgentDetails;
import com.parcel.domain.LoginResponse;
import com.parcel.domain.ParcelDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgentService {

    void deleteAgent(String id);
    LoginResponse login(String userName, String passWord);
    AgentDetails addEntry(AgentDetails agentDetails);
    List<ParcelDetails> getParcelDetailsByAgentId(String id);
    ParcelDetails changeStatus(ParcelDetails parcelDetails);
    List<AgentDetails> getAllAgentDetails();
}
