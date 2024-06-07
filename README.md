# math-ariadne
Ariadne Math é um projeto open source para avaliação de expressões lógicas e aritméticas em Java. Utilizando um lexer, parser e avaliador, ele suporta operadores matemáticos, lógicos e comparações de datas. Ideal para sistemas de regras, análises de dados e motores de jogos. Licenciado sob Apache License 2.0.

```
curl --location 'http://localhost:8080/api/v1/math/evaluate' \
--header 'Content-Type: application/json' \
--data '{
    "expression": "a == 1.0 && b != 3.0",
    "variables": {
        "a": 1.0,
        "b": 3.0
    }
}'
```