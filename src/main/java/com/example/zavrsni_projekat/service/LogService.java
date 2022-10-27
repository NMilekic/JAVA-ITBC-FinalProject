package com.example.zavrsni_projekat.service;

import com.example.zavrsni_projekat.model.Log;
import com.example.zavrsni_projekat.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<Log> findAll(){
        var logs = logRepository.findAll();
//        List<Log> logList = StreamSupport
//                .stream(logs.spliterator(), false)
//                .collect(Collectors.toList());
        return logs;
    }
}
