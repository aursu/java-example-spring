package com.github.aursu.spring.webapp.services;

import com.github.aursu.spring.webapp.services.expression.Expression;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ExpressionService {
    private Expression expression;

    public double calculate(String input) throws ParseException {
        expression = new Expression(input);

        if (expression.isValid()) {
            return expression.value();
        }
        else {
            throw new ParseException("Invalid expression", expression.getErrorOffset());
        }
    }

    public String rpnValue() {
        return expression.rpn();
    }
}
