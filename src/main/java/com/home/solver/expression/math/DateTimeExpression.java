package com.home.solver.expression.math;

import java.time.LocalDateTime;
import java.util.Map;

public class DateTimeExpression implements Expression {
    private final LocalDateTime value;

    public DateTimeExpression(LocalDateTime value) {
        this.value = value;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {
        return value;
    }
}