package org.example.checker;

import org.example.Matrix;
import org.example.MatrixTransformations;
import org.example.matrix.*;
import org.example.operators.MatrixMultiplication;
import org.example.operators.matrixmultiplication.DenseMatrixMultiplication;
import org.example.operators.matrixmultiplication.SparseMatrixMultiplication;

import java.util.List;

public class Checker {

    public static boolean testDense(Matrix a , Matrix b, Matrix c){
        MatrixMultiplication multiplier = new DenseMatrixMultiplication();
        Matrix ab = multiplier.multiply(a,b);
        Matrix ab_c = multiplier.multiply(ab, c);
        Matrix bc = multiplier.multiply(b,c);
        Matrix a_bc = multiplier.multiply(a,bc);
        return areMatricesEqual(ab_c, a_bc);
    }

    public static boolean areMatricesEqual(Matrix matrix1, Matrix matrix2){
        double epsilon = 1E-8;
        DenseMatrix a = (DenseMatrix) matrix1;
        DenseMatrix b = (DenseMatrix) matrix2;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size(); j++) {
                if ((Math.abs(a.get(i, j) - b.get(i, j)) > epsilon)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean testSparseMultiply(CompressorCRSMatrix a , CompressorCCSMatrix b, CoordinateMatrix c){
        MatrixMultiplication multiplier = new SparseMatrixMultiplication();
        Matrix ab = multiplier.multiply(a, b);
        Matrix abCRS = new MatrixTransformations().transformCOO_CRS((CoordinateMatrix) ab);
        Matrix cCCS = new MatrixTransformations().transformCOO_CCS(c);
        Matrix ab_c = multiplier.multiply(abCRS, cCCS);

        Matrix btoCOO = new MatrixTransformations().transformCCS_COO(b);
        Matrix btoCRS = new MatrixTransformations().transformCOO_CRS((CoordinateMatrix) btoCOO);
        Matrix bc = multiplier.multiply(btoCRS, cCCS);
        Matrix bctoCCS = new MatrixTransformations().transformCOO_CCS((CoordinateMatrix) bc);
        Matrix a_bc = multiplier.multiply(a, bctoCCS);

        return areSparseMatricesEqual(ab_c, a_bc);
    }

    private static boolean areSparseMatricesEqual(Matrix matrix1, Matrix matrix2) {
        double epsilon = 1E-8;
        CoordinateMatrix a = (CoordinateMatrix) matrix1;
        CoordinateMatrix b = (CoordinateMatrix) matrix2;
        List<Coordinate> coordinates1 = a.coordinates;
        List<Coordinate> coordinates2 = b.coordinates;

        for (int i = 0; i < coordinates1.size(); i++) {
            Coordinate coord1 = coordinates1.get(i);
            Coordinate coord2 = coordinates2.get(i);

            if (coord1.i() != coord2.i() || coord1.j() != coord2.j() || Math.abs(coord1.value() - coord2.value()) > epsilon) {
                return false;
            }
        }
        return true;
    }


}
