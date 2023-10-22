package sparseMatrix;

import org.example.Matrix;
import org.example.MatrixTransformations;
import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.CompressorCRSMatrix;
import org.example.matrix.CoordinateMatrix;
import org.example.matrixbuilders.CoordinateMatrixGenerator;
import org.example.matrixbuilders.MatrixMarketReader;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class BenchMarkStateSparse {

    @State(Scope.Thread)
    public static class Operands {
        @Param({"cage10.mtx", "ri2010.mtx", "Zhao1.mtx", "OPF_10000.mtx", "copter2.mtx", "bcircuit.mtx", "ncvxqp3.mtx", "rajat28.mtx", "rajat17.mtx" })
        String file;

        public Matrix matrixA;
        public Matrix matrixB;

        @Setup
        public void setup() {
            MatrixMarketReader matrixMarketReader = new MatrixMarketReader(file);
            matrixMarketReader.readMatrixMarketFile();
            Matrix a = matrixMarketReader.get();

            MatrixTransformations transformer = new MatrixTransformations();
            CompressorCRSMatrix crsMatrixA = transformer.transformCOO_CRS((CoordinateMatrix) a);
            CompressorCCSMatrix ccsMatrixB = transformer.transformCOO_CCS((CoordinateMatrix) a);

            matrixA = crsMatrixA;
            matrixB = ccsMatrixB;
        }

    }
}
