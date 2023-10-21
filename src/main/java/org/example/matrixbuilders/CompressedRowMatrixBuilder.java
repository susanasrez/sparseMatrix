package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.matrix.CompressorCRSMatrix;
import org.example.matrix.Coordinate;

public class CompressedRowMatrixBuilder extends SparseMatrixBuilder{
    private int[] rowPointers;
    private int[] colInd;
    private double[] values;
    private int[] rowStarts;
    private int[] rowEnds;

    public CompressedRowMatrixBuilder(int size) {
        super(size);
        rowPointers = new int[size + 1];
        rowStarts = new int[size];
        rowEnds = new int[size];
    }

    @Override
    public void set(int i, int j, double value) {
        coordinates.add(new Coordinate(i, j, value));
    }

    @Override
    public CompressorCRSMatrix get() {
        colInd = new int[coordinates.size()];
        values = new double[coordinates.size()];
        fillCRSArrays();
        calculateRowEnds();
        return new CompressorCRSMatrix(size, rowPointers, colInd, values);
    }

    private void fillCRSArrays() {
        int columnIndex = 0;

        for (Coordinate coordinate : coordinates) {
            int i = coordinate.i();
            int j = coordinate.j();
            double value = coordinate.value();

            rowStarts[i]++;
            colInd[columnIndex] = j;
            values[columnIndex] = value;

            columnIndex++;
        }
    }

    private void calculateRowEnds() {
        int cumSum = 0;

        for (int i = 0; i < size; i++) {
            rowEnds[i] = cumSum + rowStarts[i];
            rowPointers[i] = cumSum;
            cumSum = rowEnds[i];
        }

        rowPointers[size] = coordinates.size();
    }

    public void setMatrix(Matrix coordinateMatrix) {
        for (int i = 0; i < coordinateMatrix.size(); i++) {
            for (int j = 0; j < coordinateMatrix.size(); j++) {
                double value = coordinateMatrix.get(i, j);
                if (value != 0) {
                    coordinates.add(new Coordinate(i, j, value));
                }
            }
        }
    }
}
