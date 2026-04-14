package com.example.demobtl.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Model")
@Data
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String version;
    private Double accuracy;

    @Column(name = "precision_val")
    private Double precision;

    private Double recall;
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String trainConfig;

    @OneToMany(mappedBy = "model")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Fingerprint> fingerprints;
}
