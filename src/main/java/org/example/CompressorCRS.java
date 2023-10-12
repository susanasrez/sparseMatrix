package org.example;

import java.util.Arrays;

public class CompressorCRS {

    public int[] row_ptr;
    public int[] colInd;
    public double[] values;
    public int numRows;
    public int numCols;
    public double[][] matrix;

    public CompressorCRS(double[][] originalMatrix){
        numRows = originalMatrix.length;
        numCols = originalMatrix[0].length;
        row_ptr = new int[numRows+1];
        matrix = originalMatrix;
    }

    public void setUp(Boolean print) {
        Nonzero();
        CRSMatrix();
        if (print){
            printCRS();
        }
    }

    public void Nonzero(){
        int nnz = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] != 0) {
                    nnz++;
                }
            }
        }
        colInd = new int[nnz];
        values = new double[nnz];
    }

    public void CRSMatrix(){
        int nnzIndex = 0;
        row_ptr[0] = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] != 0) {
                    colInd[nnzIndex] = j;
                    values[nnzIndex] = matrix[i][j];
                    nnzIndex++;
                }
            }
            row_ptr[i + 1] = nnzIndex;
        }
    }

    public void printCRS() {
        System.out.println("Row pointers (rowPtr): " + Arrays.toString(row_ptr));
        System.out.println("Column indices (colInd): " + Arrays.toString(colInd));
        System.out.println("Values: " + Arrays.toString(values));
    }
}
