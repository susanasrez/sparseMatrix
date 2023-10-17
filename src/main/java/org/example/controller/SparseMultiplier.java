package org.example.controller;

import java.util.*;

public class SparseMultiplier {

    private static List<Integer> row_ptrA;
    private static ArrayList<Integer> col_indA;
    private static ArrayList<Double> valuesA;
    private static List<Integer> row_ptrB;
    private static ArrayList<Integer> col_indB;
    private static ArrayList<Double> valuesB;

    public static Map<String, ArrayList<?>> result;


    public static Map<String, ArrayList<?>> multiplyCRS(Map<String, ArrayList<?>> matrixA, Map<String, ArrayList<?>> matrixB,
                                                        int numRowsA, int numRowsB, int numColsB) {
        obtainParams(matrixA, matrixB);

        if (numRowsA != numRowsB) {
            throw new IllegalArgumentException("The dimensions of the matrices are not compatible for multiplication.");
        }

        ArrayList<Double> resultValues = new ArrayList<>();
        ArrayList<Integer> resultColInd = new ArrayList<>();
        ArrayList<Integer> resultRowPtr = new ArrayList<>();

        for (int i = 0; i < numRowsA; i++) {
            resultRowPtr.add(resultValues.size());

            for (int j = 0; j < numColsB; j++) {
                double dotProduct = 0.0;

                int startA = row_ptrA.get(i);
                int endA = row_ptrA.get(i + 1);

                for (int idxA = startA; idxA < endA; idxA++) {
                    int colA = col_indA.get(idxA);
                    double valueA = valuesA.get(idxA);

                    int startB = row_ptrB.get(colA);
                    int endB = row_ptrB.get(colA + 1);

                    for (int idxB = startB; idxB < endB; idxB++) {
                        int colB = col_indB.get(idxB);
                        double valueB = valuesB.get(idxB);

                        if (j == colB) {
                            dotProduct += valueA * valueB;
                            break;
                        } else if (j < colB) {
                            break;
                        }
                    }
                }

                if (dotProduct != 0.0) {
                    resultValues.add(dotProduct);
                    resultColInd.add(j);
                }
            }
        }

        resultRowPtr.add(resultValues.size());

        Map<String, ArrayList<?>> result = new HashMap<>();
        result.put("values", resultValues);
        result.put("col_ind", resultColInd);
        result.put("row_ptr", resultRowPtr);

        return result;
    }


    private static void obtainParams(Map<String, ArrayList<?>> matrixA, Map<String, ArrayList<?>> matrixB) {
        row_ptrA = (List<Integer>) matrixA.get("row_ptr");
        col_indA = (ArrayList<Integer>) matrixA.get("col_ind");
        valuesA= (ArrayList<Double>) matrixA.get("values");
        row_ptrB = (List<Integer>) matrixB.get("row_ptr");
        col_indB = (ArrayList<Integer>) matrixB.get("col_ind");
        valuesB = (ArrayList<Double>) matrixB.get("values");
    }
    public static void printCRS() {
        System.out.println("Row pointers (rowPtr): " + Arrays.toString(new ArrayList[]{result.get("row_ptr")}));
        System.out.println("Column indices (colInd): " + Arrays.toString(new ArrayList[]{result.get("col_ind")}));
        System.out.println("Values: " + Arrays.toString(new ArrayList[]{result.get("values")}));
    }

}
