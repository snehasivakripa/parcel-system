package com.parcel.controller;

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
public class LoginController {

    @Autowired
    AgentService agentService;

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam(name = "agentName") String agentName, @RequestParam(name = "agentPassword") String agentPassword){
        return new ResponseEntity(agentService.login(agentName,agentPassword),HttpStatus.OK);
    }


}
