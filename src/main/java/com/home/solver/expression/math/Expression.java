package com.home.solver.expression.math;

import java.util.Map;

/**
 * Expression is an interface that defines a contract for classes that represent different types of expressions in the
 * AST. Classes such as BinaryExpression, NumericConstant, and Variable implement this interface to represent different
 * types of nodes in the AST.
 *
 * Responsibilities:
 *
 * Expression (interface): Defines the evaluate method, which each type of expression must implement in order to
 * calculate its value based on a context (set of variables and their values).
 *
 * BinaryExpression: Represents a binary operation (such as addition or multiplication) between two other expressions.
 *
 * NumericConstant: Represents a constant numeric value in the expression.
 *
 * Variable: Represents a variable in the expression, whose value will be fetched from a context provided during
 * evaluation.
 */
public interface Expression {
    Comparable<?> evaluate(Map<String, Comparable<?>> variables);
}

