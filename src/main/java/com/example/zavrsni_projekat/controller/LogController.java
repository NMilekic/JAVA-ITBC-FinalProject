package com.example.zavrsni_projekat.controller;

import com.example.zavrsni_projekat.model.Client;
import com.example.zavrsni_projekat.model.Log;
import com.example.zavrsni_projekat.model.LogRequest;
import com.example.zavrsni_projekat.service.ClientService;
import com.example.zavrsni_projekat.service.LogService;
import com.example.zavrsni_projekat.service.MyClientDetailsService;
import com.example.zavrsni_projekat.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Controller
public class LogController {

    @Autowired
    private MyClientDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     * Create Log
     * @param logRequest
     * @return
     */
    @PostMapping("/api/clients/create")
    public ResponseEntity<?> createLog(@RequestBody LogRequest logRequest){
        if (logRequest.getLogType() < 0 || logRequest.getLogType() > 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Log Type must be between 0 and 2!");
        }
        if (logRequest.getMessage().length() > 6) {
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "Message should be less than 1024!");
        }
        logService.createLog(logRequest);
        throw new ResponseStatusException(HttpStatus.CREATED, "You are created log successfully!");
    }

    /**
     * Search all logs of Client
     * @param
     * @return
     */
    @GetMapping("/api/clients/search")
    public ResponseEntity<List> getAllClientLogs(){
        var logList = logService.findClientLogs();
        return ResponseEntity.status(HttpStatus.OK).body(logList);
    }

}
