package org.example.checker;

import org.example.Matrix;
import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.CompressorCRSMatrix;

public class Viewer {

    public static void showCR(CompressorCRSMatrix m) {
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

    public static void showCC(CompressorCCSMatrix m) {
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

    public static void showDense(Matrix c){
        int size = c.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < c.size(); j++) {
                System.out.print(c.get(i, j) + " ");
            }
            System.out.println();
        }
    }
}
