package com.github.aursu.spring.webapp.services.expression;

public class TokenStream {
    // input stream
    private InputReader input;

    // token start position inside input stream
    private int start = 0;

    // current token (which has been last read)
    private Token<?> current = null;

    public TokenStream(String buffer) {
        input = new InputReader(buffer);
    }

    public TokenStream(InputReader input) {
        this.input = input;
    }

    public Token<?> peek() {
        if (current == null) return next();
        return current;
    }

    public Token<?> next() {
        current = read();
        return current;
    }

    protected Token<?> read() {
        // if end of input
        if (input.eof()) return Token.EOFToken;

        // lookahead
        char peek = input.peek();

        // save start position
        setStart(input.getPos());

        // skip whitespace (for arithmetic operations is meaningless)
        if (isWhitespace(peek)) {
            input.next();  // consume whitespace
            return read();
        }

        // check if number token
        if (NumberToken.isNumber(input, current)) {
            String number = NumberToken.readNumber(input);

            if (number == null) return null;

            NumberToken token = NumberToken.getToken(number);
            if (token == null) return null;

            token.setRange(start, input.getPos());
            return token;
        }

        // check if operator
        if (OperatorToken.isOperator(input)) {
            String op = OperatorToken.read(input);

            if (op == null) return null;

            OperatorToken token = OperatorToken.getToken(op);

            token.setRange(start, input.getPos());
            return token;
        }

        // check if separator
        if (SeparatorToken.isSeparator(peek)) {
            input.next();  // consume separator

            SeparatorToken token = SeparatorToken.getToken(peek);

            token.setRange(start, input.getPos());
            return token;
        }

        return null;
    }

    public boolean isWhitespace(char ch) {
        return ch == ' ' || ch == '\t' || ch == '\n';
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String toString() {
        return input.toString();
    }
}