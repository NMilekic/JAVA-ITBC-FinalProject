package com.example.zavrsni_projekat.repository;


import com.example.zavrsni_projekat.model.Log;
import com.example.zavrsni_projekat.model.LogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;


@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    public List<Log> findAllByClientId(Integer clientId);
    List<Log> findByCreatedDateBetween(LocalDateTime to, LocalDateTime from);
    public List<Log> findByLogType(LogType logType);
}