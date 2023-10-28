package org.example.matrixbuilders;

import org.example.Matrix;
import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.Coordinate;

public class CompressedColumnMatrixBuilder extends SparseMatrixBuilder {

    public int[] columnPointers;
    public int[] rowInd;
    public double[] values;
    public int size;
    private final int[] colStarts;
    private final int[] colEnds;

    public CompressedColumnMatrixBuilder(int size) {
        super(size);
        this.size = size;
        columnPointers = new int[size + 1];
        colStarts = new int[size];
        colEnds = new int[size];
    }

    @Override
    public void set(int i, int j, double value) {
        coordinates.add(new Coordinate(i, j, value));
    }

    @Override
    public CompressorCCSMatrix get() {
        rowInd = new int[coordinates.size()];
        values = new double[coordinates.size()];
        fillCCSArrays();
        calculateColEnds();
        return new CompressorCCSMatrix(size, columnPointers, rowInd, values);
    }

    private void fillCCSArrays() {
        int rowIndex = 0;

        for (Coordinate coordinate : coordinates) {
            int i = coordinate.i();
            int j = coordinate.j();
            double value = coordinate.value();

            colStarts[j]++;
            rowInd[rowIndex] = i;
            values[rowIndex] = value;

            rowIndex++;
        }
    }

    private void calculateColEnds() {
        int cumSum = 0;

        for (int j = 0; j < size; j++) {
            colEnds[j] = cumSum + colStarts[j];
            columnPointers[j] = cumSum;
            cumSum = colEnds[j];
        }
        columnPointers[size] = coordinates.size();
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
