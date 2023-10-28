package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.MatrixBuilder;
import org.example.matrix.CoordinateMatrix;
import org.example.matrix.DenseMatrix;

public class DenseMatrixBuilder implements MatrixBuilder {

    private final int size;
    private final double[][] values;

    public DenseMatrixBuilder(int size) {
        this.size = size;
        this.values = new double[size][size];
    }

    @Override
    public void set(int i, int j, double value) {
        values[i][j] = value;
    }

    @Override
    public void setMatrix(Matrix coordinateMatrix) {
        CoordinateMatrix coordMatrix = (CoordinateMatrix) coordinateMatrix;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double value = coordMatrix.get(i, j);
                set(i, j, value);
            }
        }
    }

    @Override
    public Matrix get() {
        return new DenseMatrix(values);
    }
}
