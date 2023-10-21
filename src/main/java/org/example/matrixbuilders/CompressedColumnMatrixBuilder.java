package org.example.matrixbuilders;


import org.example.Matrix;
import org.example.matrix.CCSColumn;
import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.Coordinate;

public class CompressedColumnMatrixBuilder extends SparseMatrixBuilder {

    private CCSColumn[] columnPointers;
    private int[] rowIndices;
    private long[] values;
    private int[] colStarts;
    private int[] colEnds;


    public CompressedColumnMatrixBuilder(int size) {
        super(size);
        columnPointers = new CCSColumn[size + 1];
        rowIndices = new int[coordinates.size()];
        values = new long[coordinates.size()];
        colStarts = new int[size];
        colEnds = new int[size];
    }

    @Override
    public Matrix get() {
        fillCCSArrays();
        calculateColEnds();
        return new CompressorCCSMatrix(size, columnPointers, rowIndices, values);
    }

    private void fillCCSArrays() {
        int rowIndex = 0;

        for (Coordinate coordinate : coordinates) {
            int i = coordinate.i();
            int j = coordinate.j();
            long value = coordinate.value();

            colStarts[j]++;
            rowIndices[rowIndex] = i;
            values[rowIndex] = value;

            rowIndex++;
        }
    }

    private void calculateColEnds() {
        int cumSum = 0;

        for (int j = 0; j < size; j++) {
            colEnds[j] = cumSum + colStarts[j];
            cumSum = colEnds[j];
            columnPointers[j] = new CCSColumn(colStarts[j], colEnds[j]);
        }
    }
}
