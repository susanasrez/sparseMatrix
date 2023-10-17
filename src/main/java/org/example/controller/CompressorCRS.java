package org.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompressorCRS {

    private int[] row_ptr;
    private int[] colInd;
    private double[] values;
    private Map<String, List<?>> CRSMatrix;
    private int numRows;
    private int numCols;

    public CompressorCRS(Map<String, ArrayList<?>> cooMatrix, int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        CRSMatrix = new HashMap<>();

        setupCRS(cooMatrix);
    }

    public void setupCRS(Map<String, ArrayList<?>> cooMatrix) {
        List<Integer> rows = (List<Integer>) cooMatrix.get("Rows");
        ArrayList<Integer> columns = (ArrayList<Integer>) cooMatrix.get("Columns");
        ArrayList<Double> cooValues = (ArrayList<Double>) cooMatrix.get("Values");

        int nnz = rows.size();
        colInd = new int[nnz];
        values = new double[nnz];

        for (int i = 0; i < nnz; i++) {
            colInd[i] = columns.get(i) - 1;
            values[i] = cooValues.get(i);
        }

        initializeRowPtr(rows);

        CRSMatrix.put("row_ptr", Arrays.asList(row_ptr));
        CRSMatrix.put("col_ind", Arrays.asList(colInd));
        CRSMatrix.put("values", Arrays.asList(values));
    }

    private void initializeRowPtr(List<Integer> rows) {
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

    public Map<String, List<?>> getCRSMatrix() {
        return CRSMatrix;
    }
}
