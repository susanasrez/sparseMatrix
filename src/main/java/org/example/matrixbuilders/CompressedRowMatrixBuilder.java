package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.matrix.CCSColumn;
import org.example.matrix.CRSRow;
import org.example.matrix.CompressorCRSMatrix;
import org.example.matrix.Coordinate;

public class CompressedRowMatrixBuilder extends SparseMatrixBuilder{
    private CRSRow[] rowPointers;
    private int[] colIndices;
    private long[] values;
    private int[] rowStarts;
    private int[] rowEnds;

    public CompressedRowMatrixBuilder(int size) {
        super(size);
        rowPointers = new CRSRow[size + 1];
        colIndices = new int[coordinates.size()];
        values = new long[coordinates.size()];
        rowStarts = new int[size];
        rowEnds = new int[size];
    }

    @Override
    public Matrix get() {
        fillCRSArrays();
        calculateRowEnds();
        return new CompressorCRSMatrix(size, rowPointers, colIndices, values);
    }

    private void fillCRSArrays() {
        int columnIndex = 0;

        for (Coordinate coordinate : coordinates) {
            int i = coordinate.i();
            int j = coordinate.j();
            long value = coordinate.value();

            rowStarts[i]++;
            colIndices[columnIndex] = j;
            values[columnIndex] = value;

            columnIndex++;
        }
    }

    private void calculateRowEnds() {
        int cumSum = 0;

        for (int i = 0; i < size; i++) {
            rowEnds[i] = cumSum + rowStarts[i];
            cumSum = rowEnds[i];
            rowPointers[i] = new CRSRow(rowStarts[i], rowEnds[i]);
        }

        rowPointers[size] = new CRSRow(rowEnds[size - 1], rowEnds[size - 1]);
    }
}
