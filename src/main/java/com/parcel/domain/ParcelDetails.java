package com.parcel.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parcel_details")
@Data
public class ParcelDetails {

    @Id
    private String parcelId;
    private String parcelItem;
    private String parcelStatus;
    private String parcelAddress;
    private String parcelNumber;
    private String agentId;
    private String agentName;
    private String agentNumber;
}
