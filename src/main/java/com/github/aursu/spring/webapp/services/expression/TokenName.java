package com.github.aursu.spring.webapp.services.expression;

/* For the task we need just a few lexical tokens
 * see https://en.wikipedia.org/wiki/Lexical_analysis#Token
 * for most common ones
 */
public enum TokenName {
    SEPARATOR,
    OPERATOR,
    NUMBER, // literal
    EOF // special token EOFToken (end of input)
}
