package org.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DecompressorCRS {

    public double[][] convertCRStoMatrix(int[] row_ptr, int[] colInd, double[] values){
        int numRows = row_ptr.length-1;
        int numCols = row_ptr.length-1;

        double[][] decompressedMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = row_ptr[i]; j < row_ptr[i + 1]; j++) {
                int colIndex = colInd[j];
                double value = values[j];
                decompressedMatrix[i][colIndex] = value;
            }
        }

        return decompressedMatrix;
    }

    public static Map<String, Object> convertCRSToCOO(int[] row_ptr, int[] colInd, double[] values, int numRows, int numCols) {
        ArrayList<Integer> cooRows = new ArrayList<>();
        ArrayList<Integer> cooColumns = new ArrayList<>();
        ArrayList<Double> cooValues = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            int start = row_ptr[i];
            int end = row_ptr[i + 1];
            for (int j = start; j < end; j++) {
                int col = colInd[j];
                double value = values[j];

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
