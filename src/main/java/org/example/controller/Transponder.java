package org.example.controller;

import java.util.ArrayList;

public class Transponder {

    public ArrayList<Integer> transposedRows;
    public ArrayList<Integer> transposedColumns;
    public ArrayList<Double> transposedValues;

    public Transponder() {
        transposedRows = new ArrayList<>();
        transposedColumns = new ArrayList<>();
        transposedValues = new ArrayList<>();
    }
    public void transposeCOO(ArrayList<Integer> rows, ArrayList<Integer> columns, ArrayList<Double> values) {
        transposedRows = columns;
        transposedColumns = rows;
        transposedValues = values;
    }

    public void printTransposedCOO() {
        System.out.println("Transposed COO format:");
        for (int i = 0; i < transposedRows.size(); i++) {
            int row = transposedRows.get(i);
            int col = transposedColumns.get(i);
            double value = transposedValues.get(i);
            System.out.println("Row: " + row + ", Col: " + col + ", Value: " + value);
        }
    }


}
