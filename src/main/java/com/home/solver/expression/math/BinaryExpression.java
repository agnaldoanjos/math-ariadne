package com.home.solver.expression.math;

import java.util.Map;

public class BinaryExpression implements Expression {
    private Expression left;
    private Operator operator;
    private Expression right;

    public BinaryExpression(Expression left, Operator operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Comparable<?> evaluate(Map<String, Comparable<?>> variables) {
        Number leftValue = (Number) left.evaluate(variables);
        Number rightValue = (Number) right.evaluate(variables);
        switch (operator) {
            case PLUS:
                return leftValue.doubleValue() + rightValue.doubleValue();
            case MINUS:
                return leftValue.doubleValue() - rightValue.doubleValue();
            case MULTIPLY:
                return leftValue.doubleValue() * rightValue.doubleValue();
            case DIVIDE:
                return leftValue.doubleValue() / rightValue.doubleValue();
            case POWER:
                return Math.pow(leftValue.doubleValue(), rightValue.doubleValue());
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }
    }
}