package com.github.aursu.spring.webapp.services.expression;

/*
 * The table with operator precedence and associativity
 * (see also https://en.wikipedia.org/wiki/Order_of_operations for details)
 */
public enum TokenOperationOrder {
    EQ_EQ (TokenOperator.EQ_EQ, 2, TokenOperationAssociativity.ASSOC_LEFT), // equality equal to
    EQ_NE (TokenOperator.EQ_NE, 2, TokenOperationAssociativity.ASSOC_LEFT), // equality not equal to
    CMP_GT (TokenOperator.CMP_GT, 3, TokenOperationAssociativity.ASSOC_LEFT), // comparison greater than
    CMP_LT (TokenOperator.CMP_LT, 3, TokenOperationAssociativity.ASSOC_LEFT), // comparison less than
    CMP_GTE (TokenOperator.CMP_GTE, 3, TokenOperationAssociativity.ASSOC_LEFT), // comparison greater than or equal to
    CMP_LTE (TokenOperator.CMP_LTE, 3, TokenOperationAssociativity.ASSOC_LEFT), // comparison less than or equal to
    OP_PLUS (TokenOperator.OP_PLUS, 4, TokenOperationAssociativity.ASSOC_LEFT), // plus operation
    OP_MINUS (TokenOperator.OP_MINUS, 4, TokenOperationAssociativity.ASSOC_LEFT), // minus operation
    OP_MULT (TokenOperator.OP_MULT, 5, TokenOperationAssociativity.ASSOC_LEFT), // multiplication operation
    OP_DIV (TokenOperator.OP_DIV, 5, TokenOperationAssociativity.ASSOC_LEFT), // division operation
    OP_POWER (TokenOperator.OP_POWER, 6, TokenOperationAssociativity.ASSOC_RIGHT); // power operator ^

    // Operator	Precedence	Associativity
    public final TokenOperator operator;
    public final int precedence;
    public final TokenOperationAssociativity assoc;

    TokenOperationOrder(TokenOperator operator, int prec, TokenOperationAssociativity assoc) {
        this.operator = operator;
        this.precedence = prec;
        this.assoc =  assoc;
    }
}
