package com.github.aursu.spring.webapp.services.expression;

public class InputReader {
    private String buffer;
    private int count;
    private int pos = 0;

    public InputReader(String buffer) {
        setInput(buffer);
    }

    public void setInput(String buffer) {
        this.buffer = buffer;
        count = buffer.length();
        pos = 0;
    }

    public char peek(int n) {
        int index = pos + Math.min(count - pos, n < 0 ? 0 : n);

        if (index < count)
            return buffer.charAt(index);

        return 0;
    }

    public char peek() {
        return peek(0);
    }

    public int getPos() {
        return pos;
    }

    public char next() {
        char c = peek();

        // avoid to increase current position unnecessary
        if (c != 0) pos++;

        return c;
    }

    public boolean eof() {
        return peek() == 0;
    }

    public String toString() {
        return buffer;
    }
}