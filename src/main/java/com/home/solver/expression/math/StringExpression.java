package com.home.solver.expression.math;

import java.util.Map;

public class StringExpression implements Expression {
    private final String value;

    public StringExpression(String value) {
        this.value = value;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {
        return value;
    }
}