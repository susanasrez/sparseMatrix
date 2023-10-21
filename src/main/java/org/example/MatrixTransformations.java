package org.example;

import org.example.matrix.*;
import org.example.matrixbuilders.CompressedColumnMatrixBuilder;
import org.example.matrixbuilders.CompressedRowMatrixBuilder;
import org.example.matrixbuilders.DenseMatrixBuilder;

public class MatrixTransformations {

    public DenseMatrix transform(CoordinateMatrix matrix) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.coordinates) {
            builder.set(coordinate.i(),coordinate.j(),coordinate.value());
        }
        return (DenseMatrix) builder.get();
    }

    public CompressorCRSMatrix transformCRS_COO(CoordinateMatrix matrix){
        CompressedRowMatrixBuilder builder = new CompressedRowMatrixBuilder(matrix.size);
        for (Coordinate coordinate : matrix.coordinates) {
            builder.set(coordinate.i(),coordinate.j(),coordinate.value());
        }

        return builder.get();
    }

    public CompressorCCSMatrix transformCCS_COO(CoordinateMatrix matrix){
        CompressedColumnMatrixBuilder builder = new CompressedColumnMatrixBuilder(matrix.size);
        for (Coordinate coordinate : matrix.coordinates) {
            builder.set(coordinate.i(),coordinate.j(),coordinate.value());
        }
        return builder.get();
    }
}
