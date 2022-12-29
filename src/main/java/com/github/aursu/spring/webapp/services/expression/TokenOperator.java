package com.github.aursu.spring.webapp.services.expression;

/*
 * define required for basic arithmetic expressions operators
 * plus add power operator (to inject most common operator  with right associativity)
 */
enum TokenOperator {
    OP_PLUS, // plus operation
    OP_MINUS, // minus operation
    OP_MULT, // multiplication operation
    OP_DIV, // division operation
    OP_POWER, // power operator
    CMP_GT, // comparison greater than
    CMP_LT, // comparison less than
    CMP_GTE, // comparison greater than or equal to
    CMP_LTE, // comparison less than or equal to
    EQ_EQ, // equality equal to
    EQ_NE // equality not equal to
}
