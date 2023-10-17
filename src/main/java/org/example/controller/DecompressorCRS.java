package org.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DecompressorCRS {

    public double[][] convertCRStoMatrix(Map<String, ArrayList<?>> crsMatrix){
        ArrayList<?> rowPtrList = crsMatrix.get("row_ptr");
        ArrayList<?> colIndList = crsMatrix.get("col_ind");
        ArrayList<?> valuesList = crsMatrix.get("values");

        int numRows = rowPtrList.size() - 1;
        int numCols = rowPtrList.size() - 1;

        double[][] decompressedMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = (int) rowPtrList.get(i); j < (int) rowPtrList.get(i + 1); j++) {
                int colIndex = (int) colIndList.get(j);
                double value = (double) valuesList.get(j);
                decompressedMatrix[i][colIndex] = value;
            }
        }

        return decompressedMatrix;
    }

    public static Map<String, Object> convertCRSToCOO(Map<String, ArrayList<?>> crsMatrix, int numRows) {
        ArrayList<Integer> cooRows = new ArrayList<>();
        ArrayList<Integer> cooColumns = new ArrayList<>();
        ArrayList<Double> cooValues = new ArrayList<>();

        ArrayList<?> rowPtrList = crsMatrix.get("row_ptr");
        ArrayList<?> colIndList = crsMatrix.get("col_ind");
        ArrayList<?> valuesList = crsMatrix.get("values");

        for (int i = 0; i < numRows; i++) {
            int start = (int) rowPtrList.get(i);
            int end = (int) rowPtrList.get(i + 1);
            for (int j = start; j < end; j++) {
                int col = (int) colIndList.get(j);
                double value = (double) valuesList.get(j);

                cooRows.add(i + 1);
                cooColumns.add(col + 1);
                cooValues.add(value);
            }
        }

        Map<String, Object> cooDictionary = new HashMap<>();
        cooDictionary.put("Rows", cooRows);
        cooDictionary.put("Columns", cooColumns);
        cooDictionary.put("Values", cooValues);

        return cooDictionary;
    }


}
