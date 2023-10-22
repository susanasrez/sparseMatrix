package sparse;

import org.example.Matrix;
import org.example.MatrixTransformations;
import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.CompressorCRSMatrix;
import org.example.matrix.CoordinateMatrix;
import org.example.matrixbuilders.CoordinateMatrixGenerator;
import org.openjdk.jmh.annotations.*;

public class BenchmarkStateSparse {

    @State(Scope.Thread)
    public static class Operands {
        @Param({"64", "128", "256" , "512","1024"})
        int n;

        @Param({"0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9"})
        double density;

        Matrix matrixA;
        Matrix matrixB;

        @Setup
        public void setup() {
            matrixA = CoordinateMatrixGenerator.generateRandomCoordinateMatrix(n, density);
            matrixB = CoordinateMatrixGenerator.generateRandomCoordinateMatrix(n, density);

            MatrixTransformations transformer = new MatrixTransformations();
            CompressorCRSMatrix crsMatrixA = transformer.transformCOO_CRS((CoordinateMatrix) matrixA);
            CompressorCCSMatrix ccsMatrixB = transformer.transformCOO_CCS((CoordinateMatrix) matrixB);

            matrixA = crsMatrixA;
            matrixB = ccsMatrixB;
        }

    }
}
