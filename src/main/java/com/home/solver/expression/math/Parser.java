package com.home.solver.expression.math;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The Parser, or syntactic analyzer, receives the list of tokens from the lexer and builds an abstract syntactic tree (AST).
 * This tree represents the hierarchical structure of the mathematical expression according to the grammar of the defined language.
 * defined language.
 *
 * Responsibilities:
 *
 * Verify that the sequence of tokens follows the grammatical rules of the language.
 * Building the AST from the tokens, where each node represents different grammatical constructs (such as expressions, binary operations, etc.).
 * Manage the precedence of mathematical operations and groupings by parentheses.
 */
public class Parser {
    private final List<Token> tokens;
    private int position;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    public Expression parse() {
        Expression expr = parseExpression();
        if (position != tokens.size() - 1) {
            throw new IllegalArgumentException("Unexpected token at the end of expression");
        }
        return expr;
    }

    private Expression parseExpression() {
        Expression left = parseLogical();
        return left;
    }

    private Expression parseLogical() {
        Expression left = parseComparison();
        while (position < tokens.size() && (tokens.get(position).getType() == TokenType.AND || tokens.get(position).getType() == TokenType.OR)) {
            Token operator = tokens.get(position++);
            Expression right = parseComparison();
            left = new LogicalExpression(left, right, Operator.fromSymbol(operator.getValue()));
        }
        return left;
    }

    private Expression parseComparison() {
        Expression left = parseArithmetic();
        while (position < tokens.size() && isComparisonOperator(tokens.get(position).getType())) {
            Token operator = tokens.get(position++);
            Expression right = parseArithmetic();
            left = new ComparisonExpression(left, right, Operator.fromSymbol(operator.getValue()));
        }
        return left;
    }

    private boolean isComparisonOperator(TokenType type) {
        return type == TokenType.EQUALS || type == TokenType.NOT_EQUALS || type == TokenType.GREATER_THAN ||
                type == TokenType.LESS_THAN || type == TokenType.GREATER_EQUAL || type == TokenType.LESS_EQUAL;
    }

    private Expression parseArithmetic() {
        Expression left = parseTermArithmetic();
        while (position < tokens.size() && (tokens.get(position).getType() == TokenType.PLUS || tokens.get(position).getType() == TokenType.MINUS)) {
            Token operator = tokens.get(position++);
            Expression right = parseTermArithmetic();
            left = new ArithmeticExpression(left, right, Operator.fromSymbol(operator.getValue()));
        }
        return left;
    }

    private Expression parseTermArithmetic() {
        Expression left = parseFactorArithmetic();
        while (position < tokens.size() && (tokens.get(position).getType() == TokenType.MULTIPLY || tokens.get(position).getType() == TokenType.DIVIDE || tokens.get(position).getType() == TokenType.POWER)) {
            Token operator = tokens.get(position++);
            Expression right = parseFactorArithmetic();
            left = new ArithmeticExpression(left, right, Operator.fromSymbol(operator.getValue()));
        }
        return left;
    }

    private Expression parseFactorArithmetic() {
        Token token = tokens.get(position++);
        if (token.getType() == TokenType.NUMBER) {
            return new NumberExpression(Double.parseDouble(token.getValue()));
        } else if (token.getType() == TokenType.IDENTIFIER) {
            return new VariableExpression(token.getValue());
        } else if (token.getType() == TokenType.STRING) {
            return new StringExpression(token.getValue());
        } else if (token.getType() == TokenType.DATE_TIME) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            return new DateTimeExpression(LocalDateTime.parse(token.getValue(), formatter));
        } else if (token.getType() == TokenType.LPAREN) {
            Expression expr = parseExpression();
            if (tokens.get(position).getType() != TokenType.RPAREN) {
                throw new IllegalArgumentException("Expected closing parenthesis");
            }
            position++;
            return expr;
        }
        throw new IllegalArgumentException("Unexpected token: " + token.getValue());
    }
}