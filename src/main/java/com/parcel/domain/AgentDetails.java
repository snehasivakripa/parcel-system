package com.parcel.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "agent_details")
@Data
public class AgentDetails {

    @Id
    private String agentId;
    private String agentName;
    private String agentPassword;
    private String agentNumber;
    private String role;
}
