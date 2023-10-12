package org.example;

public class DecompressorCRS {

    public double[][] decompressMatrix(int[] row_ptr, int[] colInd, double[] values){
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


}
