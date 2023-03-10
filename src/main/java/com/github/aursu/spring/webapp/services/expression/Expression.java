package com.github.aursu.spring.webapp.services.expression;

import java.text.ParseException;

public class Expression extends RPNParser {
    // expression representation
    // infix is standard  arithmetic form (see https://en.wikipedia.org/wiki/Infix_notation)
    // polish is Reverse Polish notation
    private String infix = null;

    // calculated expression value
    private Double value = null;

    // if expression is valid
    private boolean valid = false,
                    empty = false;

    private int errorOffset = -1;

    public Expression(String input) {
        super(input);
        setup();
    }

    public Expression(InputReader input) {
        super(input);
        setup();
    }

    private void setup() {
        try {
            parse();

            valid = true;
            value = result.doubleValue();
            infix = buffer;
        } catch (ParseException e) {
            if (buffer.isEmpty())
                empty = true;
            errorOffset = e.getErrorOffset();
        }
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Double value() {
        return value;
    }

    public String infix() {
        return infix;
    }

    public int getErrorOffset() {
        return errorOffset;
    }
}