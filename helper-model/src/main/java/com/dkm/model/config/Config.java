package com.dkm.model.config;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String cron;
}
