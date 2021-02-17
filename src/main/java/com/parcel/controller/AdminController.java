package com.parcel.controller;

import com.parcel.domain.AgentDetails;
import com.parcel.domain.ParcelDetails;
import com.parcel.service.AgentService;
import com.parcel.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/parcel")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    ParcelService parcelService;

    @Autowired
    AgentService agentService;

    @GetMapping("/parcelDetails")
    public ResponseEntity getAllParcelDetails() {
        try {
            return new ResponseEntity(parcelService.getAllParcelDetails(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/agentDetails")
    public ResponseEntity getAllAgentDetails() {
        try {
            log.info(agentService.getAllAgentDetails().toString());
            return new ResponseEntity(agentService.getAllAgentDetails(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/addDeliveryEntry")
    public ResponseEntity addDeliveryEntry(@RequestBody ParcelDetails parcelDetails){
        try {
            return new ResponseEntity(parcelService.addEntry(parcelDetails), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addAgent")
    public ResponseEntity addAgent(@RequestBody AgentDetails agentDetails){
        try {
            return new ResponseEntity(agentService.addEntry(agentDetails), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeAgent/{id}")
    public ResponseEntity deleteAgent(@PathVariable String id){
        try {
            agentService.deleteAgent(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/deleteParcelEntries/{id}")
    public ResponseEntity deleteParcelEntries(@PathVariable String id){
        try {
            parcelService.deleteParcelEntries(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
