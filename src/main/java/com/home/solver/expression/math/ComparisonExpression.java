package com.home.solver.expression.math;

import java.util.Map;

public class ComparisonExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final Operator operator;

    public ComparisonExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {
        Comparable leftValue = (Comparable) left.evaluate(variables);
        Comparable rightValue = (Comparable) right.evaluate(variables);

        return switch (operator) {
            case EQUALS -> leftValue.compareTo(rightValue) == 0;
            case NOT_EQUALS -> leftValue.compareTo(rightValue) != 0;
            case GREATER_THAN -> leftValue.compareTo(rightValue) > 0;
            case LESS_THAN -> leftValue.compareTo(rightValue) < 0;
            case GREATER_EQUAL -> leftValue.compareTo(rightValue) >= 0;
            case LESS_EQUAL -> leftValue.compareTo(rightValue) <= 0;
            default -> throw new IllegalArgumentException("Unsupported comparison operator: " + operator);
        };
    }
}
