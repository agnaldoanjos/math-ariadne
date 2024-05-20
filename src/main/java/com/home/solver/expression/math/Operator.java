package com.home.solver.expression.math;

public enum Operator {
    EQUALS("=="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_EQUAL(">="),
    LESS_EQUAL("<="),
    AND("&&"),
    OR("||"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POWER("^");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Operator fromSymbol(String symbol) {
        for (Operator op : values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + symbol);
    }
}
