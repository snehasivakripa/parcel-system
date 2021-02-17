package com.parcel.service;

import com.parcel.domain.ParcelDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParcelService {

    List<ParcelDetails> getAllParcelDetails();

    ParcelDetails addEntry(ParcelDetails parcelDetails);

    void deleteParcelEntries(String id);


}
