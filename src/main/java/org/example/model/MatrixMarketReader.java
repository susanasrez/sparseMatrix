package org.example.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MatrixMarketReader {

    private String filePath;
    public int n;
    public ArrayList<Integer> rows;
    public ArrayList<Integer> columns;
    public ArrayList<Double> values;

    public MatrixMarketReader(String path) {
        filePath = path;
        rows = new ArrayList<>();
        columns = new ArrayList<>();
        values = new ArrayList<>();
    }

    public void readMatrixMarketFile() {

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
                    } else{
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
        for (int i = 0; i < rows.size(); i++) {
            int row = rows.get(i);
            int col = columns.get(i);
            double value = values.get(i);
            System.out.println("Row: " + row + ", Col: " + col + ", Value: " + value);
        }
    }

    public void constructionMatrix(String[] parts){
        if (parts.length == 3) {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            double value = Double.parseDouble(parts[2]);
            rows.add(row);
            columns.add(col);
            values.add(value);
        }
    }
}
