package org.example.matrix;

import java.util.stream.IntStream;

public class CompressorCCSMatrix extends SparseMatrix {

    public final int size;
    public final int[] columnPointers;
    public final int[] rowInd;
    public final double[] values;

    public CompressorCCSMatrix(int size, int[] columnPointers, int[] rowIndices, double[] values) {
        this.size = size;
        this.columnPointers = columnPointers;
        this.rowInd = rowIndices;
        this.values = values;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double get(int i, int j) {
        int colStart = columnPointers[j];
        int colEnd = columnPointers[j+1];

        return IntStream.range(colStart, colEnd)
                .filter(k -> rowInd[k] == i)
                .mapToDouble(k -> values[k])
                .findFirst()
                .orElse(0L);
    }
}
