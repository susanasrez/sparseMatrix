package org.example.matrix;

import org.example.Matrix;

public class DenseMatrix implements Matrix {
    private final long[][] values;

    public DenseMatrix(long[][] values) {
        this.values = values;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public long get(int i, int j) {
        return values[i][j];
    }
}
