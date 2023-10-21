package org.example.controller;

import org.example.Matrix;
import org.example.MatrixBuilder;
import org.example.MatrixOperations;
import org.example.MatrixTransformations;
import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.CompressorCRSMatrix;
import org.example.matrix.CoordinateMatrix;
import org.example.matrix.DenseMatrix;
import org.example.matrixbuilders.*;
import org.example.operators.MatrixMultiplication;
import org.example.operators.matrixmultiplication.DenseMatrixMultiplication;

import java.util.Random;


public class Controller {

    public Controller(String path){
        MatrixBuilder x = new CoordinateMatrixGenerator();
        CoordinateMatrix c = ((CoordinateMatrixGenerator) x).generateRandomCoordinateMatrix(3, 0.3);
        show(c);
        System.out.println();
        CoordinateMatrix d = ((CoordinateMatrixGenerator) x).generateRandomCoordinateMatrix(3, 0.3);
        show(d);
        System.out.println();
        Matrix a = new MatrixTransformations().transform(c);
        Matrix b = new MatrixTransformations().transform(c);
        Matrix e = new MatrixOperations().multiply(a,b);
        Boolean good = test(a,b,e);
        show(e);
        System.out.println(good);
    }

    public void show(Matrix c){
        int size = c.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(c.get(i, j) + "\t");
            }
            System.out.println();
        }
    }
    public void showCC(CompressorCCSMatrix m) {
        int[] columnPointers = m.columnPointers;
        int[] rowIndices = m.rowInd;
        double[] values = m.values;

        System.out.println("Column Pointers:");
        for (int column : columnPointers) {
            System.out.print(column + " ");
        }
        System.out.println();

        System.out.println("Row Indices:");
        for (int row : rowIndices) {
            System.out.print(row + " ");
        }
        System.out.println();

        System.out.println("Values:");
        for (double value : values) {
            System.out.print(value + " ");
        }
    }

    public void showCR(CompressorCRSMatrix m) {
        int[] rowPointers = m.rowPointers;
        int[] colInd = m.colInd;
        double[] values = m.values;

        System.out.println("Row Pointers:");
        for (int row : rowPointers) {
            System.out.print(row + " ");
        }
        System.out.println();

        System.out.println("Col Indices:");
        for (int col : colInd) {
            System.out.print(col + " ");
        }
        System.out.println();

        System.out.println("Values:");
        for (double value : values) {
            System.out.print(value + " ");
        }
    }

    public boolean test(Matrix a , Matrix b, Matrix c){
        Matrix vector = vectorobtain(a);

        Matrix result = multiplyTets(a,vector);
        Matrix res2 = multiplyTets(result, b);
        System.out.println("Res 1");
        show(res2);
        System.out.println();
        Matrix res3 = multiplyTets(c,vector);
        System.out.println(" Res 2");
        show(res3);
        System.out.println();

        return res2.equals(res3);
    }

    public Matrix vectorobtain(Matrix a){
        double[][] values = new double[a.size()][1];
        Random random = new Random();
        for (int i = 0; i < a.size(); i++) {
            values[i][0] = random.nextDouble(); // Genera un valor aleatorio entre 0.0 y 1.0
        }

        return new DenseMatrix(values);
    }
    public Matrix multiplyTets(Matrix matrix, Matrix vector) {
        DenseMatrix a = (DenseMatrix) matrix;
        DenseMatrix v = (DenseMatrix) vector;
        double[][] result = new double[1][a.size()];

        for (int i = 0; i < a.size(); i++) {
            result[0][i] = 0.0;
            for (int j = 0; j < a.size(); j++) {
                result[0][i] += a.get(i, j) * v.get(j, 0);
            }
            System.out.println(result[0][i]);
            System.out.println("----");
        }

        return new DenseMatrix(result);
    }

}
