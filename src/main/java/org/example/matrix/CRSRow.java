package org.example.matrix;

public record CRSRow(int rowStart, int rowEnd) {
    public int rowStart() {
        return rowStart;
    }
    public int rowEnd() {
        return rowEnd;
    }
}
