package com.project.gymweb.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
public class Imc {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;
    private String gender;
    private Double weight;
    private Double height;
    private Double value;
    private Date date;
    @ManyToOne
    private User user;
}
