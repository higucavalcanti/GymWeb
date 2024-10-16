package com.project.gymweb.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "supplements")
@Data
public class Supplement {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    private String name;
    private String description;
    private String usageIndications;
    private String nutritionalTips;
}
