package org.example;

import org.example.controller.CompressorCRS;
import org.example.matrix.*;
import org.example.operators.matrixmultiplication.DenseMatrixMultiplication;
import org.example.operators.matrixmultiplication.SparseMatrixMultiplication;

public class MatrixOperations {

    public static Matrix multiply(Matrix a, Matrix b) {
        if (a instanceof DenseMatrix && b instanceof DenseMatrix) {
            return (Matrix) new DenseMatrixMultiplication().multiply(a, b);
        }
        else if (a instanceof CompressorCRSMatrix && b instanceof CompressorCCSMatrix) {
            return new SparseMatrixMultiplication().multiply(a, b);
            }
        else if (a instanceof CompressorCCSMatrix && b instanceof CompressorCRS) {
            throw new IllegalArgumentException("Multiplication should be CRS x CCS.");
        } else if (a instanceof CoordinateMatrix || b instanceof CoordinateMatrix) {
            throw new IllegalArgumentException("Please transform the matrices into CRS and CCS format.");
        }
        else {
            return null;
        }
    }
}
