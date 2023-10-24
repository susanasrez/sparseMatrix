package org.example.operators.matrixmultiplication;

import org.example.Matrix;
import org.example.MatrixBuilder;
import org.example.matrix.*;
import org.example.matrixbuilders.CoordinateMatrixBuilder;
import org.example.operators.MatrixMultiplication;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication implements MatrixMultiplication {
    @Override
    public Matrix multiply(Matrix matrix_a, Matrix matrix_b) {
        CompressorCRSMatrix a = (CompressorCRSMatrix) matrix_a;
        CompressorCCSMatrix b = (CompressorCCSMatrix) matrix_b;
        MatrixBuilder builder = new CoordinateMatrixBuilder(a.size);
        List<Coordinate> coordinateList = new ArrayList<>();

        for (int i = 0; i < a.size; i++) {
            for (int j = 0; j < b.size; j++) {
                int rowStart= a.rowPointers[i];
                int rowEnd = a.rowPointers[i+1];
                int colStart= b.columnPointers[j];
                int colEnd = b.columnPointers[j+1];
                double s = 0;

                while (rowStart < rowEnd && colStart < colEnd){
                    int aa = a.colInd[rowStart];
                    int bb = b.rowInd[colStart];
                    if(aa == bb) {
                        s += a.values[rowStart] * b.values[colStart];
                        rowStart++;
                        colStart++;
                    }
                    else if ( aa < bb) rowStart++;
                    else colStart++;
                }
                if (s!= 0) builder.set(i, j, s);
            }
        }
        return builder.get();
    }
}
