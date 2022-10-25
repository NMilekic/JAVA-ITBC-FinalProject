package com.example.zavrsni_projekat.controller;

import com.example.zavrsni_projekat.model.Log;
import com.example.zavrsni_projekat.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public ResponseEntity<Void> createLog(@RequestBody Log log){
        logService.createLog(log);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Search all logs of Client
     * @param
     * @return
     */
    @RequestMapping("/api/clients/search")
    public String search(){

        return "radi";
    }

}
