package org.example;

import org.example.matrix.*;
import org.example.matrixbuilders.CompressedColumnMatrixBuilder;
import org.example.matrixbuilders.CompressedRowMatrixBuilder;
import org.example.matrixbuilders.DenseMatrixBuilder;

import java.util.ArrayList;
import java.util.List;

public class MatrixTransformations {

    public DenseMatrix transform(CoordinateMatrix matrix) {
        DenseMatrixBuilder builder = new DenseMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.coordinates) {
            builder.set(coordinate.i(),coordinate.j(),coordinate.value());
        }
        return (DenseMatrix) builder.get();
    }

    public CompressorCRSMatrix transformCOO_CRS(CoordinateMatrix matrix){
        CompressedRowMatrixBuilder builder = new CompressedRowMatrixBuilder(matrix.size);
        for (Coordinate coordinate : matrix.coordinates) {
            builder.set(coordinate.i(),coordinate.j(),coordinate.value());
        }

        return builder.get();
    }

    public CompressorCCSMatrix transformCOO_CCS(CoordinateMatrix matrix){
        CompressedColumnMatrixBuilder builder = new CompressedColumnMatrixBuilder(matrix.size);
        for (Coordinate coordinate : matrix.coordinates) {
            builder.set(coordinate.i(),coordinate.j(),coordinate.value());
        }
        return builder.get();
    }

    public CoordinateMatrix transformCRS_COO(CompressorCRSMatrix matrix){
        int size = matrix.size;
        List<Coordinate> coordinates = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int rowStart = matrix.rowPointers[i];
            int rowEnd = matrix.rowPointers[i + 1];

            for (int k = rowStart; k < rowEnd; k++) {
                int j = matrix.colInd[k];
                double value = matrix.values[k];
                coordinates.add(new Coordinate(i, j, value));
            }
        }

        return new CoordinateMatrix(size, coordinates);
    }

    public CoordinateMatrix transformCCS_COO(CompressorCCSMatrix matrix){
        int size = matrix.size;
        List<Coordinate> coordinates = new ArrayList<>();

        for (int j = 0; j < size; j++) {
            int colStart = matrix.columnPointers[j];
            int colEnd = matrix.columnPointers[j + 1];

            for (int k = colStart; k < colEnd; k++) {
                int i = matrix.rowInd[k];
                double value = matrix.values[k];
                coordinates.add(new Coordinate(i, j, value));
            }
        }

        return new CoordinateMatrix(size, coordinates);
    }
}
