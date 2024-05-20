package com.home.solver.controller;

import com.home.solver.expression.math.Expression;
import com.home.solver.expression.math.Lexer;
import com.home.solver.expression.math.Parser;
import com.home.solver.expression.math.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/math")
public class ExpressionController {

    @PostMapping("/evaluate")
    public ResponseEntity<String> evaluateExpression(@RequestBody Map<String, Object> payload) {
        try {
            String expression = (String) payload.get("expression");
            Map<String, Comparable<?>> variables = (Map<String, Comparable<?>>) payload.get("variables");

            Lexer lexer = new Lexer(expression);
            List<Token> tokens = lexer.tokenize();
            Parser parser = new Parser(tokens);
            Expression expr = parser.parse();
            Comparable<?> result = expr.evaluate(variables);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}