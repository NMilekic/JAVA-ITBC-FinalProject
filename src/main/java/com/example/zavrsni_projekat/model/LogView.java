package com.example.zavrsni_projekat.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogView {

    private String message;
    private LogType logType;
    private LocalDateTime createdDate;

    public LogView(String message, LogType logType, LocalDateTime createdDate) {
        this.message = message;
        this.logType = logType;
        this.createdDate = createdDate;
    }
}
