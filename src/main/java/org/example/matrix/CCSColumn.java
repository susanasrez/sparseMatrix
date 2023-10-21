package org.example.matrix;

public record CCSColumn(int colStart, int colEnd) {
    public int colStart() {
        return colStart;
    }

    public int colEnd() {
        return colEnd;
    }
}
