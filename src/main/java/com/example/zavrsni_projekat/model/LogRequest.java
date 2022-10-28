package com.example.zavrsni_projekat.model;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
public class LogRequest {
    private String message;
    private Integer logType;

    public LogType getLogTypeEnum() {
        switch (logType) {
            case 0 : return LogType.ERROR;
            case 1 : return LogType.WARNING;
            case 2 : return LogType.INFO;
            default:
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
        }

    }
}
