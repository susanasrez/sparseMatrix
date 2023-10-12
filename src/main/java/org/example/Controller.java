package org.example;


public class Controller {

    public Controller(String path){

        MatrixMarketReader matrixMarketReader = new MatrixMarketReader(path);
        matrixMarketReader.readMatrixMarketFile();
        double[][] matrix = matrixMarketReader.matrix;

        //matrixMarketReader.showMatrix();

        //CompressorCRS compressor = new CompressorCRS(matrix);
        //compressor.setUp(Boolean.TRUE);
        //CompressorCCS compressorCCS = new CompressorCCS(matrix);
        //compressorCCS.setUp(Boolean.TRUE);

        double[][] matrixtransposed = Transponder.transposeMatrix(matrix);
        CompressorCRS compressor = new CompressorCRS(matrix);
        compressor.setUp(Boolean.TRUE);
        CompressorCRS compressor2 = new CompressorCRS(matrixtransposed);
        compressor2.setUp(Boolean.TRUE);

    }
}
