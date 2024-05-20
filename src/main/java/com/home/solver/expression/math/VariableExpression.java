package com.home.solver.expression.math;

import com.home.solver.util.Utils;

import java.util.Map;

public class VariableExpression implements Expression {
    private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {

        Comparable value = variables.get(name) != null && Utils.isValidDateTimeFormat(variables.get(name).toString()) ? Utils.parseDateTime(variables.get(name).toString().replace("[","").replace("]","")) : variables.get(name);

        if (value == null) {
            throw new IllegalArgumentException("Variable " + name + " not defined");
        }
        return value;
    }
}
