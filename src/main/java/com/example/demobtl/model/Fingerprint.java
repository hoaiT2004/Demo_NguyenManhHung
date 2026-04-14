package com.example.demobtl.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "Fingerprint")
@Data
public class Fingerprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fingerIndex;

    @Column(columnDefinition = "TEXT")
    private String vectorData;

    @OneToOne
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Model model;
}
