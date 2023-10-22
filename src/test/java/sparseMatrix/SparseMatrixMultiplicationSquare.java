package sparseMatrix;

import org.example.Matrix;
import org.example.MatrixOperations;
import org.openjdk.jmh.annotations.Benchmark;
import sparse.BenchmarkStateRandomSparse;

public class SparseMatrixMultiplicationSquare {

    @Benchmark
    public void multiplication(BenchMarkStateSparse.Operands operands) {
        Matrix matrixA = operands.matrixA;
        Matrix matrixB = operands.matrixB;

        Matrix result = new MatrixOperations().multiply(matrixA, matrixB);

    }
}
