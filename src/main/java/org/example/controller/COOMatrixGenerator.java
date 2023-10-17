package org.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class COOMatrixGenerator {

    public static Map<String, Object> generateRandomCOOMatrix(int n, double density) {
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

        Map<String, Object> cooMatrix = new HashMap<>();
        cooMatrix.put("Rows", cooRows);
        cooMatrix.put("Columns", cooColumns);
        cooMatrix.put("Values", cooValues);

        return cooMatrix;
    }

    public static void showResult(Map<String,Object> cooMatrix){
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
}
