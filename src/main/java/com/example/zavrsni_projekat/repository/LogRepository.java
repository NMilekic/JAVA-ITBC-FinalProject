package com.example.zavrsni_projekat.repository;


import com.example.zavrsni_projekat.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

}