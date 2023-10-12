package org.example;


import javax.swing.plaf.synth.SynthOptionPaneUI;

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
        int[] row_ptr = compressor.row_ptr;
        int[] colInd = compressor.colInd;
        double[] values = compressor.values;

        DecompressorCRS decompressorCRS = new DecompressorCRS();
        double[][] m1 = decompressorCRS.decompressMatrix(row_ptr, colInd, values);


    }
}
