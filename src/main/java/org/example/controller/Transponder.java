package org.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Transponder {

    public Map<String, ArrayList<?>> transposedMatrix;

    public Transponder() {
        transposedMatrix = new HashMap<>();
        transposedMatrix.put("Rows", new ArrayList<>());
        transposedMatrix.put("Columns", new ArrayList<>());
        transposedMatrix.put("Values", new ArrayList<>());
    }
    public void transposeCOO(Map<String, ArrayList<?>> matrix) {
        transposedMatrix.put("Rows", matrix.get("Columns"));
        transposedMatrix.put("Columns", matrix.get("Rows"));
        transposedMatrix.put("Values", matrix.get("Values"));
    }
    public Map<String, ArrayList<?>> getTransposedCOO() {
        return transposedMatrix;
    }


    public void printTransposedCOO() {
        System.out.println("Transposed COO format:");
        ArrayList<Integer> rows = (ArrayList<Integer>) transposedMatrix.get("Rows");
        ArrayList<Integer> columns = (ArrayList<Integer>) transposedMatrix.get("Columns");
        ArrayList<Double> values = (ArrayList<Double>) transposedMatrix.get("Values");

        for (int i = 0; i < rows.size(); i++) {
            int row = rows.get(i);
            int col = columns.get(i);
            double value = values.get(i);
            System.out.println("Row: " + row + ", Col: " + col + ", Value: " + value);
        }
    }

}
