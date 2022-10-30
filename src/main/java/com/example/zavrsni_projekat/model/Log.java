package com.example.zavrsni_projekat.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="log")
public class Log {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "logId")
    private int logId;
    private String message;
    private LogType logType;
    private LocalDateTime createdDate;

//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = true)
//    private Client client;

    @Column(name = "client_id")
    private int clientId;

}
