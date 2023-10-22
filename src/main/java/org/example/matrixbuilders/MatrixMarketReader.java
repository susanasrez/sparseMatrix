package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.MatrixBuilder;
import org.example.matrix.CoordinateMatrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;


public class MatrixMarketReader implements MatrixBuilder {
    private String filePath;
    private int n;
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
                        n = Integer.parseInt(parts[0]);
                        matrixBuilder = new CoordinateMatrixBuilder(n);
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
    public void setMatrix(Matrix c) {

    }

    @Override
    public Matrix get() {
        return matrixBuilder.get();
    }


}
