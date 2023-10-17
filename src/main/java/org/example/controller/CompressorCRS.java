package org.example.controller;

import java.util.*;

public class CompressorCRS {

    private int[] row_ptr;
    private int[] colInd;
    private double[] values;
    private final int numRows;
    public CompressorCRS(Map<String, ArrayList<?>> cooMatrix, int numRows) {
        this.numRows = numRows;

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

    public Map<String, ArrayList<?>> getCRSMatrix() {
        Map<String, ArrayList<?>> CRSMatrix = new HashMap<>();

        ArrayList<Integer> row_ptrList = new ArrayList<>();
        for (int value : row_ptr) {
            row_ptrList.add(value);
        }

        ArrayList<Integer> colIndList = new ArrayList<>();
        for (int value : colInd) {
            colIndList.add(value);
        }

        ArrayList<Double> valuesList = new ArrayList<>();
        for (double value : values) {
            valuesList.add(value);
        }

        CRSMatrix.put("row_ptr", row_ptrList);
        CRSMatrix.put("col_ind", colIndList);
        CRSMatrix.put("values", valuesList);

        return CRSMatrix;
    }

}
