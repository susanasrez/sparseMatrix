package org.example.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatrixMarketReader {

    private String filePath;
    public int n;
    public Map<String, ArrayList<?>> matrix;

    public MatrixMarketReader(String path) {
        filePath = path;
        matrix = new HashMap<>();
        matrix.put("Rows", new ArrayList<>());
        matrix.put("Columns", new ArrayList<>());
        matrix.put("Values", new ArrayList<>());
    }

    public void readMatrixMarketFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean notdata = true;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("%")) {
                    continue;
                } else {
                    String[] parts = line.trim().split("\\s+");
                    if (notdata) {
                        notdata = false;
                        n = Integer.parseInt(parts[0]);
                    } else {
                        constructionMatrix(parts);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMatrix() {
        System.out.println("Matrix (in COO format):");
        ArrayList<Integer> rows = (ArrayList<Integer>) matrix.get("row");
        ArrayList<Integer> columns = (ArrayList<Integer>) matrix.get("col");
        ArrayList<Double> values = (ArrayList<Double>) matrix.get("values");

        for (int i = 0; i < rows.size(); i++) {
            int row = rows.get(i);
            int col = columns.get(i);
            double value = values.get(i);
            System.out.println("Row: " + row + ", Col: " + col + ", Value: " + value);
        }
    }

    public void constructionMatrix(String[] parts) {
        if (parts.length == 3) {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            double value = Double.parseDouble(parts[2]);
            ((ArrayList<Integer>) matrix.get("Rows")).add(row);
            ((ArrayList<Integer>) matrix.get("Columns")).add(col);
            ((ArrayList<Double>) matrix.get("Values")).add(value);
        }
    }

    public Map<String, ArrayList<?>> getMatrixData() {
        return matrix;
    }
}
