package com.github.aursu.spring.webapp.services.expression;



/*
 * Token<TokenType> is a general class for all tokens we will use
 */
public class Token<T> {
    // special EOFToken
    public static final Token<Void> EOFToken =  new Token<>(TokenName.EOF, null);

    // token name is rather its type
    protected TokenName name;
    protected T value;

    // optional (for error reporting)
    private int start = -1,
                end = -1;

    public Token(TokenName name, T value) {
        this.setName(name);
        this.setValue(value);
    }

    public TokenName getName() {
        return name;
    }

    public void setName(TokenName name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getStart() {
        return start;
    }

    // store starting token position inside input string
    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    // store end token position inside input string
    public void setEnd(int end) {
        this.end = end;
    }

    public void setRange(int start, int end) {
        setStart(start);
        setEnd(end);
    }

    public boolean isNumber() {
        return name == TokenName.NUMBER;
    }

    public boolean isOperator() {
        return name == TokenName.OPERATOR;
    }

    public boolean isSeparator() {
        return name == TokenName.SEPARATOR;
    }

    public boolean equals(Token<?> token)
    {
        if (token == null) return false;
        return getName() == token.getName() && getValue() == token.getValue();
    }

    public String toString() {
        switch(name) {
            case SEPARATOR: return SeparatorToken.toString((TokenSeparator) value);
            case OPERATOR: return OperatorToken.toString((TokenOperator) value);
            case NUMBER: return value.toString();
            case EOF: return "\n";
        }
        return null;
    }
}