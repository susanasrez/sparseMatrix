package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.matrix.Coordinate;
import org.example.matrix.CoordinateMatrix;

public class CoordinateMatrixBuilder extends SparseMatrixBuilder {

    public CoordinateMatrixBuilder(int size) {
        super(size);
    }

    @Override
    public void set(int i, int j, double value) {
        Coordinate coordinate = new Coordinate(i, j, value);
        set(coordinate);
    }

    @Override
    public Matrix get() {
        return new CoordinateMatrix(size, coordinates).getByRows();
    }
    public Matrix getByCols() {
        return new CoordinateMatrix(size, coordinates).getSortCol();
    }

    public void setMatrix(Matrix coordinateMatrix) {
    }
}
