package com.example.demobtl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    private String label;
    private Long count;
    private Double percentage;
}
