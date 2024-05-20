package com.home.solver.expression.math;

import java.util.ArrayList;
import java.util.List;

/**
 * The Lexer, or lexical analyzer, is responsible for reading the text input (the mathematical expression) and
 * converting it into a sequence of tokens. This conversion is based on defined rules that identify character patterns
 * that make up the different types of tokens.
 *
 * Responsibilities:
 *
 * Analyze the input string and identify and extract tokens.
 * Ignore whitespace or other delimiters that are not significant for syntactic analysis.
 * Generate a list of tokens that will be used by the parser.
 */
public class Lexer {
    private final String input;
    private int position;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (position < input.length()) {
            char currentChar = input.charAt(position);
            if (Character.isWhitespace(currentChar)) {
                position++;
            } else if (Character.isDigit(currentChar) || currentChar == '.') {
                tokens.add(tokenizeNumber());
            } else if (Character.isLetter(currentChar)) {
                tokens.add(tokenizeIdentifier());
            } else if (currentChar == '\'') {
                tokens.add(tokenizeString());
            } else if (currentChar == '[') {
                tokens.add(tokenizeDateTime());
            } else {
                tokens.add(tokenizeSymbol());
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private Token tokenizeNumber() {
        StringBuilder number = new StringBuilder();
        while (position < input.length() && (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')) {
            number.append(input.charAt(position++));
        }
        return new Token(TokenType.NUMBER, number.toString());
    }

    private Token tokenizeIdentifier() {
        StringBuilder identifier = new StringBuilder();
        while (position < input.length() && Character.isLetterOrDigit(input.charAt(position))) {
            identifier.append(input.charAt(position++));
        }
        return new Token(TokenType.IDENTIFIER, identifier.toString());
    }

    private Token tokenizeString() {
        StringBuilder str = new StringBuilder();
        position++; // Skip the opening quote
        while (position < input.length() && input.charAt(position) != '\'') {
            str.append(input.charAt(position++));
        }
        if (position == input.length() || input.charAt(position) != '\'') {
            throw new IllegalArgumentException("Unterminated string literal");
        }
        position++; // Skip the closing quote
        return new Token(TokenType.STRING, str.toString());
    }

    private Token tokenizeDateTime() {
        StringBuilder dateTime = new StringBuilder();
        position++; // Skip the opening bracket
        while (position < input.length() && input.charAt(position) != ']') {
            dateTime.append(input.charAt(position++));
        }
        if (position == input.length() || input.charAt(position) != ']') {
            throw new IllegalArgumentException("Unterminated date-time literal");
        }
        position++; // Skip the closing bracket
        return new Token(TokenType.DATE_TIME, dateTime.toString());
    }

    private Token tokenizeSymbol() {
        char currentChar = input.charAt(position);
        position++;
        if (currentChar == '+') return new Token(TokenType.PLUS, "+");
        if (currentChar == '-') return new Token(TokenType.MINUS, "-");
        if (currentChar == '*') return new Token(TokenType.MULTIPLY, "*");
        if (currentChar == '/') return new Token(TokenType.DIVIDE, "/");
        if (currentChar == '^') return new Token(TokenType.POWER, "^");
        if (currentChar == '(') return new Token(TokenType.LPAREN, "(");
        if (currentChar == ')') return new Token(TokenType.RPAREN, ")");
        if (currentChar == '&' && input.charAt(position) == '&') {
            position++;
            return new Token(TokenType.AND, "&&");
        }
        if (currentChar == '|' && input.charAt(position) == '|') {
            position++;
            return new Token(TokenType.OR, "||");
        }
        if (currentChar == '=' && input.charAt(position) == '=') {
            position++;
            return new Token(TokenType.EQUALS, "==");
        }
        if (currentChar == '!' && input.charAt(position) == '=') {
            position++;
            return new Token(TokenType.NOT_EQUALS, "!=");
        }
        if (currentChar == '>') {
            if (input.charAt(position) == '=') {
                position++;
                return new Token(TokenType.GREATER_EQUAL, ">=");
            }
            return new Token(TokenType.GREATER_THAN, ">");
        }
        if (currentChar == '<') {
            if (input.charAt(position) == '=') {
                position++;
                return new Token(TokenType.LESS_EQUAL, "<=");
            }
            return new Token(TokenType.LESS_THAN, "<");
        }
        throw new IllegalArgumentException("Unexpected character: " + currentChar);
    }
}
