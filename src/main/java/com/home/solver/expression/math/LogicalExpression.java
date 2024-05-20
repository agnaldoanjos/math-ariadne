package com.home.solver.expression.math;

import java.util.Map;

public class LogicalExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final Operator operator;

    public LogicalExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {
        Boolean leftValue = (Boolean) left.evaluate(variables);
        Boolean rightValue = (Boolean) right.evaluate(variables);

        return switch (operator) {
            case AND -> leftValue && rightValue;
            case OR -> leftValue || rightValue;
            default -> throw new IllegalArgumentException("Unsupported logical operator: " + operator);
        };
    }
}
