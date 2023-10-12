package org.example;

import java.util.Arrays;

public class CompressorCCS {
    public int[] col_ptr;
    public int[] rowInd;
    public double[] values;
    public int numRows;
    public int numCols;
    public double[][] matrix;

    public CompressorCCS(double[][] originalMatrix) {
        numRows = originalMatrix.length;
        numCols = originalMatrix[0].length;
        col_ptr = new int[numCols+1];
        matrix = originalMatrix;
    }

    public void setUp(Boolean print) {
        Nonzero();
        CCSMatrix();
        if(print){
            printCCS();
        }
    }

    public void Nonzero(){
        int nnz = 0;
        for (int j = 0; j < numCols; j++) {
            for (int i = 0; i < numRows; i++) {
                if (matrix[i][j] != 0) {
                    nnz++;
                }
            }
        }
        rowInd = new int[nnz];
        values = new double[nnz];
    }

    public void CCSMatrix() {
        int nnzIndex = 0;
        col_ptr[0] = 0;

        for (int j = 0; j < numCols; j++) {
            for (int i = 0; i < numRows; i++) {
                if (matrix[i][j] != 0) {
                    rowInd[nnzIndex] = i;
                    values[nnzIndex] = matrix[i][j];
                    nnzIndex++;
                }
            }
            col_ptr[j + 1] = nnzIndex;
        }
    }

    public void printCCS() {
        System.out.println("Column pointers (col_ptr): " + Arrays.toString(col_ptr));
        System.out.println("Row indices (rowInd): " + Arrays.toString(rowInd));
        System.out.println("Values: " + Arrays.toString(values));
    }

}
