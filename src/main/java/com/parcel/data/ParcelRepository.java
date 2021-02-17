package com.parcel.data;

import com.parcel.domain.ParcelDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends MongoRepository<ParcelDetails,String> {
    List<ParcelDetails> findByAgentId(String agentId);
    ParcelDetails findByParcelId(String id);
}
