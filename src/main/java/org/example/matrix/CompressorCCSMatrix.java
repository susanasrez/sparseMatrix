package org.example.matrix;


import java.util.stream.IntStream;

public class CompressorCCSMatrix extends SparseMatrix {
    public final int size;

    public final CCSColumn[] columnPointers;
    public final int[] rowInd;
    public final long[] values;

    public CompressorCCSMatrix(int size, CCSColumn[] columnPointers, int[] rowIndices, long[] values) {
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
    public long get(int i, int j) {
        int colStart = columnPointers[j].colStart();
        int colEnd = columnPointers[j + 1].colEnd();

        return IntStream.range(colStart, colEnd)
                .filter(k -> rowInd[k] == i)
                .mapToLong(k -> values[k])
                .findFirst()
                .orElse(0L);
    }


}
