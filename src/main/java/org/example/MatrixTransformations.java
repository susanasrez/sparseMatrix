package org.example;

import org.example.matrix.Coordinate;
import org.example.matrix.CoordinateMatrix;
import org.example.matrix.DenseMatrix;
import org.example.matrixbuilders.CoordinateMatrixBuilder;
import org.example.matrixbuilders.DenseMatrixBuilder;

public class MatrixTransformations {
    public CoordinateMatrix transform(DenseMatrix matrix) {
        CoordinateMatrixBuilder builder = new CoordinateMatrixBuilder(matrix.size());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                if (matrix.get(i,j) == 0) continue;
                builder.set(new Coordinate(i,j, matrix.get(i,j)));
            }
        }
        return (CoordinateMatrix) builder.get();
    }

    public DenseMatrix transform(CoordinateMatrix matrix) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.coordinates) {
            builder.set(coordinate.i(),coordinate.j(),coordinate.value());
        }
        return (DenseMatrix) builder.get();
    }
}
