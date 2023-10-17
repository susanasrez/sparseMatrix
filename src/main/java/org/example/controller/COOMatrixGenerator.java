package org.example.controller;

import java.util.*;

public class COOMatrixGenerator {

    public static Map<String, ArrayList<?>> generateRandomCOOMatrix(int n, double density) {
        if (density < 0.0 || density > 1.0) {
            throw new IllegalArgumentException("Density must be in the range [0, 1].");
        }

        ArrayList<Integer> cooRows = new ArrayList<>();
        ArrayList<Integer> cooColumns = new ArrayList<>();
        ArrayList<Double> cooValues = new ArrayList<>();

        Random random = new Random();
        int totalNonZeroElements = (int) Math.round(n * n * density);

        for (int i = 0; i < totalNonZeroElements; i++) {
            int row = 0, col = 0;
            boolean isUnique = false;
            while (!isUnique) {
                row = random.nextInt(n) + 1;
                col = random.nextInt(n) + 1;
                isUnique = !cooRows.contains(row) && !cooColumns.contains(col);
            }

            double value = random.nextDouble();
            cooRows.add(row);
            cooColumns.add(col);
            cooValues.add(value);
        }

        sortCOOMatrixByRows(cooRows, cooColumns, cooValues);

        Map<String, ArrayList<?>> cooMatrix = new HashMap<>();
        cooMatrix.put("Rows", cooRows);
        cooMatrix.put("Columns", cooColumns);
        cooMatrix.put("Values", cooValues);

        return cooMatrix;
    }

    public static void showResult(Map<String,ArrayList<?>> cooMatrix){
        ArrayList<Integer> cooRows = (ArrayList<Integer>) cooMatrix.get("Rows");
        ArrayList<Integer> cooColumns = (ArrayList<Integer>) cooMatrix.get("Columns");
        ArrayList<Double> cooValues = (ArrayList<Double>) cooMatrix.get("Values");

        for (int i = 0; i < cooRows.size(); i++) {
            int row = cooRows.get(i);
            int col = cooColumns.get(i);
            double value = cooValues.get(i);
            System.out.println("Row: " + row + ", Col: " + col + ", Value: " + value);
        }
    }

    public static void sortCOOMatrixByRows(ArrayList<Integer> rows, ArrayList<Integer> columns, ArrayList<Double> values) {
        int n = rows.size();
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> rows.get(a).compareTo(rows.get(b)));

        ArrayList<Integer> sortedRows = new ArrayList<>();
        ArrayList<Integer> sortedColumns = new ArrayList<>();
        ArrayList<Double> sortedValues = new ArrayList<>();

        for (int i : indices) {
            sortedRows.add(rows.get(i));
            sortedColumns.add(columns.get(i));
            sortedValues.add(values.get(i));
        }

        rows.clear();
        columns.clear();
        values.clear();

        rows.addAll(sortedRows);
        columns.addAll(sortedColumns);
        values.addAll(sortedValues);
    }
}
