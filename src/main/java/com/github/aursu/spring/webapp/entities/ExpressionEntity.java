package com.github.aursu.spring.webapp.entities;

import lombok.Data;

@Data
public class ExpressionEntity {
    private String polish;
    private String infix;
    private double value;
}
