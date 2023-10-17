package org.example.controller;


import java.util.*;

public class Controller {

    public Controller(String path){

        ArrayList<Integer> rows = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 3, 3));
        ArrayList<Integer> columns = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 2, 3));
        ArrayList<Double> values = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 5.0, 8.0, 9.0));
        int numRows = 3;
        int numCols = 3;

        CompressorCRS compressorA = new CompressorCRS(createCOOMatrix(rows, columns, values), numRows);

        compressorA.printCRS();
        Map<String, ArrayList<?>> matrixA = compressorA.getCRSMatrix();
        System.out.println("-------------------------------------------------");

        ArrayList<Integer> rowsb = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2, 3, 3));
        ArrayList<Integer> columnsb = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 3, 1, 3));
        ArrayList<Double> valuesb = new ArrayList<>(Arrays.asList(2.0, 3.0, 1.0, 1.0, 4.0, 1.0, 3.0));

        CompressorCRS compressorB = new CompressorCRS(createCOOMatrix(rowsb, columnsb, valuesb), numRows);

        compressorB.printCRS();
        Map<String, ArrayList<?>> matrixB = compressorB.getCRSMatrix();


        System.out.println("----------------------------------------------------");
        System.out.println("Resultado: ");

        SparseMultiplier sparseMultiplier = new SparseMultiplier();
        sparseMultiplier.multiplyCRS(matrixA, matrixB, numRows, numRows, numCols);
        sparseMultiplier.printCRS();


    }

    public static Map<String, ArrayList<?>> createCOOMatrix(ArrayList<Integer> rows, ArrayList<Integer> columns, ArrayList<Double> values) {
        Map<String, ArrayList<?>> cooMatrix = new HashMap<>();
        cooMatrix.put("Rows", new ArrayList<>(rows));
        cooMatrix.put("Columns", new ArrayList<>(columns));
        cooMatrix.put("Values", new ArrayList<>(values));
        return cooMatrix;
    }


}
