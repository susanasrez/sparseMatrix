package dense;

import org.example.Matrix;
import org.example.MatrixTransformations;
import org.example.matrix.CoordinateMatrix;
import org.example.matrix.DenseMatrix;
import org.example.matrixbuilders.CoordinateMatrixGenerator;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class BenchmarkStateRandomDense {

    @State(Scope.Thread)
    public static class OperandsDense {
        @Param({"64", "128", "256", "512", "1024"})
        int n;

        @Param({"0.2", "0.4", "0.6", "0.8"})
        double density;

        Matrix matrixA;
        Matrix matrixB;

        @Setup
        public void setup() {
            matrixA = CoordinateMatrixGenerator.generateRandomCoordinateMatrix(n, density);
            matrixB = CoordinateMatrixGenerator.generateRandomCoordinateMatrix(n, density);

            MatrixTransformations transformer = new MatrixTransformations();
            DenseMatrix crsMatrixA = transformer.transform((CoordinateMatrix) matrixA);
            DenseMatrix ccsMatrixB = transformer.transform((CoordinateMatrix) matrixB);

            matrixA = crsMatrixA;
            matrixB = ccsMatrixB;
        }
    }
}
