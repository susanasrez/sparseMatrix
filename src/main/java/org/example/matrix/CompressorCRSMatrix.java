package org.example.matrix;

import java.util.stream.IntStream;

public class CompressorCRSMatrix extends SparseMatrix{

    public final int size;
    public final CRSRow[] rowPointers;
    public final int[] colInd;
    public final long[] values;

    public CompressorCRSMatrix(int size, CRSRow[] rowPointers, int[] colIndices, long[] values) {
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
    public long get(int i, int j) {
        int rowStart = rowPointers[i].rowStart();
        int rowEnd = rowPointers[i].rowEnd();

        return IntStream.range(rowStart, rowEnd)
                .filter(k -> colInd[k] == j)
                .mapToLong(k -> values[k])
                .findFirst()
                .orElse(0L);
    }
}
