package com.home.solver.expression.math;

import java.util.Map;

public class NumberExpression implements Expression {
    private final Double value;

    public NumberExpression(Double value) {
        this.value = value;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {
        return value;
    }
}
