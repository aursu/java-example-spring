package com.github.aursu.spring.webapp.services.expression;

public class OperatorToken extends Token<TokenOperator> {
    // operators
    public static final OperatorToken opPlus = new OperatorToken(TokenOperator.OP_PLUS);
    public static final OperatorToken opMinus = new OperatorToken(TokenOperator.OP_MINUS);
    public static final OperatorToken opMult = new OperatorToken(TokenOperator.OP_MULT);
    public static final OperatorToken opDiv = new OperatorToken(TokenOperator.OP_DIV);
    public static final OperatorToken opPow = new OperatorToken(TokenOperator.OP_POWER);

    private int precedence = -1;
    private TokenOperationAssociativity assoc = TokenOperationAssociativity.ASSOC_LEFT;

    public OperatorToken(TokenOperator value) {
        super(TokenName.OPERATOR, value);
        setAttributes();
    }

    private void setAttributes() {
        for(TokenOperationOrder o: TokenOperationOrder.values()) {
            if (value == o.operator) {
                setPrecedence(o.precedence);
                setAssoc(o.assoc);
            }
        }
    }

    public int getPrecedence() {
        return precedence;
    }

    public void setPrecedence(int precedence) {
        this.precedence = precedence;
    }

    public TokenOperationAssociativity getAssoc() {
        return assoc;
    }

    public void setAssoc(TokenOperationAssociativity assoc) {
        this.assoc = assoc;
    }

    public boolean greaterPrecedence(OperatorToken op) {
        return precedence > op.getPrecedence();
    }

    public boolean samePrecedence(OperatorToken op) {
        return precedence == op.getPrecedence();
    }

    public boolean leftAssociative() {
        return assoc == TokenOperationAssociativity.ASSOC_LEFT;
    }

    public boolean rightAssociative() {
        return assoc == TokenOperationAssociativity.ASSOC_RIGHT;
    }

    public String toString() {
        return OperatorToken.toString(value);
    }

    public static int isOperator(char ch, char lookahead) {
        switch(ch) {
            case '=', '!':
                if (lookahead == '=') return 2;
                break;
            case '>', '<':
                if (lookahead == '=') return 2;
                return 1;
            case '+', '^':
            case '*', (char) 215, (char) 183: // *, ×, ·
            case '/', (char) 247: // /, ÷
            case '-', '\u2212':   // -, −
                return 1;
        }
        return 0;
    }

    public static boolean isOperator(InputReader input) {
        char peek      = input.peek(0),
                lookahead = input.peek(1);
        return (isOperator(peek, lookahead) > 0);
    }

    public static OperatorToken getToken(char ch, char lookahead) {
        switch(ch) {
            case '+': return new OperatorToken(TokenOperator.OP_PLUS);
            case '-', '\u2212': return new OperatorToken(TokenOperator.OP_MINUS);
            case '*', (char) 215, (char) 183: return new OperatorToken(TokenOperator.OP_MULT);
            case '/', (char) 247: return new OperatorToken(TokenOperator.OP_DIV);
            case '^': return new OperatorToken(TokenOperator.OP_POWER);
            case '>':
                if (lookahead == '=') return new OperatorToken(TokenOperator.CMP_GTE);
                return new OperatorToken(TokenOperator.CMP_GT);
            case '<':
                if (lookahead == '=') return new OperatorToken(TokenOperator.CMP_LTE);
                return new OperatorToken(TokenOperator.CMP_LT);
            case '!':
                if (lookahead == '=') return new OperatorToken(TokenOperator.EQ_NE);
                break;
            case '=':
                if (lookahead == '=') return new OperatorToken(TokenOperator.EQ_EQ);
        }
        return null;
    }

    public static OperatorToken getToken(String op) {
        char peek = op.charAt(0), lookahead  = 0;
        if (op.length() > 1) lookahead = op.charAt(1);

        return getToken(peek, lookahead);
    }

    public static String toString(TokenOperator value) {
        switch(value) {
            case OP_PLUS: return "+";
            case OP_MINUS: return "-";
            case OP_MULT: return "*";
            case OP_DIV: return "/";
            case OP_POWER: return "^";
            case CMP_GT: return ">";
            case CMP_LT: return "<";
            case CMP_GTE: return ">=";
            case CMP_LTE: return "<=";
            case EQ_EQ: return "==";
            case EQ_NE: return "!=";
        }
        return null;
    }

    // >, <, >=, <=
    public static String read(InputReader input) {
        char peek      = input.peek(0),
                lookahead = input.peek(1);

        int opLen = isOperator(peek, lookahead);

        if (opLen > 0) input.next(); // consume operator
        else return null;

        String token = String.valueOf(peek);

        if (opLen == 2) {
            token += lookahead;
            input.next(); // consume lookahead
        }

        return token;
    }
}