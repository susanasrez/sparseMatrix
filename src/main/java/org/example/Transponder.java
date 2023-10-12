package org.example;

public class Transponder {

    public static double[][] transposeMatrix(double[][] originalMatrix){
        int numRows = originalMatrix.length;
        int numCols = originalMatrix[0].length;

        double[][] transposedMatrix = new double[numCols][numRows];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                transposedMatrix[j][i] = originalMatrix[i][j];
            }
        }
        return transposedMatrix;
    }

    public static void showMatrix(double[][] matrix) {
        System.out.println("Transposed matrix:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
