package com.home.solver.expression.math;

public enum TokenType {
    NUMBER,
    IDENTIFIER,
    STRING, // Token para strings
    DATE_TIME, // Token para valores de data e hora
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    POWER,
    LPAREN,
    RPAREN,
    AND,
    OR,
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    LESS_THAN,
    GREATER_EQUAL,
    LESS_EQUAL,
    EOF;

    public static TokenType fromSymbol(String symbol) {
        switch (symbol) {
            case "+": return PLUS;
            case "-": return MINUS;
            case "*": return MULTIPLY;
            case "/": return DIVIDE;
            case "^": return POWER;
            case "(": return LPAREN;
            case ")": return RPAREN;
            case "&&": return AND;
            case "||": return OR;
            case "==": return EQUALS;
            case "!=": return NOT_EQUALS;
            case ">": return GREATER_THAN;
            case "<": return LESS_THAN;
            case ">=": return GREATER_EQUAL;
            case "<=": return LESS_EQUAL;
            default: return EOF;
        }
    }
}