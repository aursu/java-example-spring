package com.github.aursu.spring.webapp.controllers;

import com.github.aursu.spring.webapp.entities.ExpressionEntity;
import com.github.aursu.spring.webapp.services.ExpressionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ExpressionController {

    private final ExpressionService expressionService;

    @PostMapping("/calculate")
    public String addExpression(@RequestParam String expression, Model model) {
        ExpressionEntity expressionEntity = new ExpressionEntity();
        expressionEntity.setInfix(expression);

        model.addAttribute("expression", expressionEntity);

        try {
            double v = expressionService.calculate(expression);

            expressionEntity.setValue(v);
            expressionEntity.setPolish(expressionService.rpnValue());

            return "show_expression";
        } catch (Exception e) {
            return "error_expression";
        }
    }
}
