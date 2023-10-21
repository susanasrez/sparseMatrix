package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.matrix.CoordinateMatrix;


public class CoordinateMatrixBuilder extends SparseMatrixBuilder {

    public CoordinateMatrixBuilder(int size) {
        super(size);
    }

    @Override
    public Matrix get() {
        return new CoordinateMatrix(size, coordinates);
    }
}
