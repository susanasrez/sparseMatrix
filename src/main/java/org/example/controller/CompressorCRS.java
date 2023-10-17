package org.example.controller;

import java.util.ArrayList;
import java.util.Arrays;

public class CompressorCRS {

    public int[] row_ptr;
    public int[] colInd;
    public double[] values;
    public int numRows;
    public int numCols;

    public CompressorCRS(ArrayList<Integer> rows, ArrayList<Integer> columns, ArrayList<Double> cooValues, int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;

        setupCRS(rows, columns, cooValues);
    }


    public void setupCRS(ArrayList<Integer> rows, ArrayList<Integer> columns, ArrayList<Double> cooValues) {
        int nnz = rows.size();
        colInd = new int[nnz];
        values = new double[nnz];

        for (int i = 0; i < nnz; i++) {
            colInd[i] = columns.get(i)-1;
            values[i] = cooValues.get(i);
        }

        initializeRowPtr(rows);
    }

    private void initializeRowPtr(ArrayList<Integer> rows) {
        row_ptr = new int[numRows + 1];
        Arrays.fill(row_ptr, 0);

        for (Integer row : rows) {
            row_ptr[row]++;
        }

        for (int i = 1; i <= numRows; i++) {
            row_ptr[i] += row_ptr[i - 1];
        }
    }

    public void printCRS() {
        System.out.println("Row pointers (rowPtr): " + Arrays.toString(row_ptr));
        System.out.println("Column indices (colInd): " + Arrays.toString(colInd));
        System.out.println("Values: " + Arrays.toString(values));
    }
}
