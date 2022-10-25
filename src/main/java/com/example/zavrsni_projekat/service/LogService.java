package com.example.zavrsni_projekat.service;

import com.example.zavrsni_projekat.model.Log;
import com.example.zavrsni_projekat.model.ClientType;
import com.example.zavrsni_projekat.model.LogType;
import com.example.zavrsni_projekat.repository.ClientRepository;
import com.example.zavrsni_projekat.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void createLog(Log log){
        if (log.getCreatedDate() == null) {
            log.setCreatedDate(LocalDateTime.now());
        }

        logRepository.save(log);
    }
}
