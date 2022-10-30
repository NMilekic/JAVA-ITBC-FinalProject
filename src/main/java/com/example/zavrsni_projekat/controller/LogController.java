package com.example.zavrsni_projekat.controller;

import com.example.zavrsni_projekat.model.Log;
import com.example.zavrsni_projekat.model.LogRequest;
import com.example.zavrsni_projekat.model.LogType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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
        if (logRequest.getMessage().length() > 1024) {
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
    public ResponseEntity<List> getAllClientLogs(@RequestParam(required = false, name = "logType") Integer logType){
        System.out.println(logType);
        if (logType == null){
            System.out.println("radi");
            var logList = logService.findClientLogs();
            return ResponseEntity.status(HttpStatus.OK).body(logList);
        }
        System.out.println("radi2");
        if (logType < 0 || logType > 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Log Type must be between 0 and 2!");
        }
        LogType logTypeParam;
        switch (logType) {
            case 0:
                logTypeParam = LogType.ERROR;
                break;
            case 1:
                logTypeParam = LogType.WARNING;
                break;
            case 2:
                logTypeParam = LogType.INFO;
                break;
            default:
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
        }
        var logList = logService.findClientLogsByType(logTypeParam);
        return ResponseEntity.status(HttpStatus.OK).body(logList);
    }

}
