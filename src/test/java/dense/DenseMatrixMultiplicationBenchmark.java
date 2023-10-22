package dense;

import org.example.Matrix;
import org.example.MatrixOperations;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(value = 1)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1)
@Measurement(iterations = 2)
public class DenseMatrixMultiplicationBenchmark {

    @Benchmark
    public void multiplication(BenchmarkStateRandomDense.OperandsDense operands) {
        Matrix matrixA = operands.matrixA;
        Matrix matrixB = operands.matrixB;

        Matrix result = new MatrixOperations().multiply(matrixA, matrixB);

    }
}
