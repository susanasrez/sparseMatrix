package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.MatrixBuilder;
import org.example.matrix.Coordinate;
import org.example.matrix.CoordinateMatrix;

import java.util.ArrayList;
import java.util.List;

public abstract class SparseMatrixBuilder implements MatrixBuilder {
    protected final int size;
    protected final List<Coordinate> coordinates;

    public SparseMatrixBuilder(int size) {
        this.size = size;
        this.coordinates = new ArrayList<>();
    }

    public void set(Coordinate coordinate) {
        coordinates.add(coordinate);
    }


}
