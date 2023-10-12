package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MatrixMarketReader {

    private String filePath;
    private int n;
    public double[][] matrix;

    public MatrixMarketReader(String path){
        filePath = path;
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
        System.out.println("Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void constructionMatrix(String[] parts){
        if (parts.length == 3) {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            double value = Double.parseDouble(parts[2]);
            if (matrix == null) {
                matrix = new double[n][n];
            }
            matrix[row][col] = value;
        }
    }
}
