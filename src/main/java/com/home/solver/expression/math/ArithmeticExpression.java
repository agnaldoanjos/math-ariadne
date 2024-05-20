package com.home.solver.expression.math;

import java.util.Map;

public class ArithmeticExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final Operator operator;

    public ArithmeticExpression(Expression left, Expression right, Operator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {
        Double leftValue = (Double) left.evaluate(variables);
        Double rightValue = (Double) right.evaluate(variables);

        return switch (operator) {
            case PLUS -> leftValue + rightValue;
            case MINUS -> leftValue - rightValue;
            case MULTIPLY -> leftValue * rightValue;
            case DIVIDE -> leftValue / rightValue;
            case POWER -> Math.pow(leftValue, rightValue);
            default -> throw new IllegalArgumentException("Unsupported arithmetic operator: " + operator);
        };
    }
}
