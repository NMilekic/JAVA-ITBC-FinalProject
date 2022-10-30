package com.example.zavrsni_projekat.service;

import com.example.zavrsni_projekat.model.*;
import com.example.zavrsni_projekat.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    @Autowired
    private MyClientDetailsService userDetailsService;

    public void createLog(LogRequest logRequest){
        Log log = new Log();
        log.setLogType(logRequest.getLogTypeEnum());
        log.setMessage(logRequest.getMessage());
        log.setClientId(userDetailsService.getUserId());
        if (log.getCreatedDate() == null) {
            log.setCreatedDate(LocalDateTime.now());
        }
        logRepository.save(log);
    }

    public List<LogView> findClientLogs(){
        var logs = logRepository.findAllByClientId(userDetailsService.getUserId());

        List<LogView> logsView = new ArrayList<>();
        for (var log : logs) {
            var logView = new LogView(
                    log.getMessage(),
                    log.getLogType(),
                    log.getCreatedDate()
            );
            logsView.add(logView);
        }

        return logsView;
    }

    public List<LogView> findClientLogsByType(LogType logType){
        var logs = logRepository.findAllByClientIdAndLogType(userDetailsService.getUserId(), logType);

        List<LogView> logsView = new ArrayList<>();
        for (var log : logs) {
            var logView = new LogView(
                    log.getMessage(),
                    log.getLogType(),
                    log.getCreatedDate()
            );
            logsView.add(logView);
        }

        return logsView;
    }
}
