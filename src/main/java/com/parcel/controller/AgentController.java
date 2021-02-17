package com.parcel.controller;


import com.parcel.domain.ParcelDetails;
import com.parcel.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/parcel")
@CrossOrigin(origins = "*")
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/agentDetailsById")
    public ResponseEntity getParcelDetailsByAgentId(@RequestParam (value="id") String id) {
        try {
            return new ResponseEntity(agentService.getParcelDetailsByAgentId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/changeStatus")
    public ResponseEntity changeStatus(@RequestBody ParcelDetails parcelDetails){
        return new ResponseEntity(agentService.changeStatus(parcelDetails),HttpStatus.OK);
    }
    }
