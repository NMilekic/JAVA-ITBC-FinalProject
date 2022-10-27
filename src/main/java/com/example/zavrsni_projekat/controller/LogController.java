package com.example.zavrsni_projekat.controller;

import com.example.zavrsni_projekat.model.Log;
import com.example.zavrsni_projekat.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     * Create Log
     * @param log
     * @return
     */
    @PostMapping("/api/clients/create")
    public ResponseEntity<?> createLog(@RequestBody Log log){
        if (log.getLogType().ordinal() < 0 && log.getLogType().ordinal() > 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Log type!");
        }
        logService.createLog(log);

        throw new ResponseStatusException(HttpStatus.CREATED, "You are created log successfully!");
    }

    /**
     * Search all logs of Client
     * @param
     * @return
     */
    @GetMapping("/api/clients/search")
    public ResponseEntity<List> getAllClientLogs(){
        var logList = logService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(logList);
    }

}
