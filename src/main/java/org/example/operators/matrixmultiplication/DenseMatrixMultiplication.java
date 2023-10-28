package org.example.operators.matrixmultiplication;

import org.example.Matrix;
import org.example.matrix.DenseMatrix;
import org.example.operators.MatrixMultiplication;

public class DenseMatrixMultiplication implements MatrixMultiplication {

    @Override
    public Matrix multiply(Matrix matrix_a, Matrix matrix_b) {
        DenseMatrix a = (DenseMatrix) matrix_a;
        DenseMatrix b = (DenseMatrix) matrix_b;
        double[][] c = new double[a.size()][b.size()];
        for (int i = 0; i < a.size(); i++) {
            for (int k = 0; k < b.size(); k++) {
                double aik = a.get(i, k);
                for (int j = 0; j < a.size();j++) {
                    c[i][j] += aik * b.get(k, j);
                }
            }
        }
        return new DenseMatrix(c);
    }
}
