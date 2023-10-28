package org.example.testSuite;

import org.example.Matrix;
import org.example.MatrixOperations;
import org.example.MatrixTransformations;
import org.example.checker.Checker;
import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.CompressorCRSMatrix;
import org.example.matrix.CoordinateMatrix;
import org.example.matrixbuilders.MatrixMarketReader;

public class TimeMarkStateSparse {

    public static class Operands {
        static String[] files={"cage10.mtx", "ri2010.mtx", "Zhao1.mtx", "OPF_10000.mtx", "copter2.mtx", "bcircuit.mtx", "ncvxqp3.mtx", "rajat28.mtx", "rajat17.mtx"};

        public static Matrix matrixA;
        public static Matrix matrixB;
        public static Matrix matrixC;

        public static void setup() {
            for (String file : files) {
                MatrixMarketReader matrixMarketReader = new MatrixMarketReader(file);
                matrixMarketReader.readMatrixMarketFile();

                MatrixTransformations transformer = new MatrixTransformations();
                CompressorCRSMatrix crsMatrixA = transformer.transformCOO_CRS((CoordinateMatrix) matrixMarketReader.get());
                CompressorCCSMatrix ccsMatrixB = transformer.transformCOO_CCS((CoordinateMatrix) matrixMarketReader.getByCols());

                matrixA = crsMatrixA;
                matrixB = ccsMatrixB;
                double time = multiplyTime(matrixA, matrixB);
                System.out.println("Time taken to multiply the matrix : " + time + " millis");
                boolean check = Checker.testSparseMultiply((CompressorCRSMatrix) matrixA, (CompressorCCSMatrix) matrixB, (CoordinateMatrix) matrixC);
                System.out.println("The test it's "+ check);

            }
        }

        public static double multiplyTime(Matrix a, Matrix b) {
            double startTime = System.currentTimeMillis();
            matrixC = MatrixOperations.multiply(a, b);
            double endTime = System.currentTimeMillis();

            return endTime - startTime;
        }
    }
}
