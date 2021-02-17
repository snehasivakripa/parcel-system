package com.parcel.data;

import com.parcel.domain.AgentDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends MongoRepository<AgentDetails, String> {

    List<AgentDetails> findByAgentName(String userName);

}
