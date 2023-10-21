package org.example.matrix;

import java.util.stream.IntStream;

public class CompressorCRSMatrix extends SparseMatrix{

    public final int size;
    public final int[] rowPointers;
    public final int[] colInd;
    public final double[] values;

    public CompressorCRSMatrix(int size, int[] rowPointers, int[] colIndices, double[] values) {
        this.size = size;
        this.rowPointers = rowPointers;
        this.colInd = colIndices;
        this.values = values;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double get(int i, int j) {
        int rowStart = rowPointers[i];
        int rowEnd = rowPointers[i];

        return IntStream.range(rowStart, rowEnd)
                .filter(k -> colInd[k] == j)
                .mapToDouble(k -> values[k])
                .findFirst()
                .orElse(0L);
    }
}
