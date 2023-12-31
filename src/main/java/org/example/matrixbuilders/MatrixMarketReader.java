package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.MatrixBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatrixMarketReader implements MatrixBuilder {

    private final String filePath;
    public CoordinateMatrixBuilder matrixBuilder;

    public MatrixMarketReader(String path) {
        filePath = "src/main/resources/" + path;
        matrixBuilder = new CoordinateMatrixBuilder(0);
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
                        int n = Integer.parseInt(parts[0]);
                        matrixBuilder = new CoordinateMatrixBuilder(n);
                    } else {
                        constructionMatrix(parts);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void constructionMatrix(String[] parts) {
        if (parts.length == 3) {
            int row = Integer.parseInt(parts[0]) - 1;
            int col = Integer.parseInt(parts[1]) - 1;
            double value = Double.parseDouble(parts[2]);
            matrixBuilder.set(row, col, value);
        }
    }

    @Override
    public void set(int i, int j, double value) {
        matrixBuilder.set(i, j, value);
    }

    @Override
    public void setMatrix(Matrix c) {}

    @Override
    public Matrix get() {
        return matrixBuilder.get();
    }

    public Matrix getByCols() {
        return matrixBuilder.getByCols();
    }
}
