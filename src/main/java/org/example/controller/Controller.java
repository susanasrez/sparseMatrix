package org.example.controller;


import org.example.model.MatrixMarketReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Controller {

    public Controller(String path){

        //MatrixMarketReader matrixMarketReader = new MatrixMarketReader(path);
        //matrixMarketReader.readMatrixMarketFile();
        //matrixMarketReader.showMatrix();

        Map<String,ArrayList<?>> xd = COOMatrixGenerator.generateRandomCOOMatrix(4, 0.2);
        COOMatrixGenerator.showResult(xd);
        Transponder transponder = new Transponder();
        transponder.transposeCOO(xd);
        transponder.printTransposedCOO();

        //CompressorCRS compressorCRS = new CompressorCRS(xd, 4, 4);
        //compressorCRS.printCRS();

        /*ArrayList<Integer> cooRows = new ArrayList<>(Arrays.asList(1, 1, 1, 2 , 3, 3));
        ArrayList<Integer> cooColumns = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 2, 3));
        ArrayList<Double> cooValues = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 5.0, 8.0, 9.0));


        CompressorCRS compressor = new CompressorCRS(cooRows, cooColumns,cooValues,
                3, 3);
        compressor.printCRS();*/


    }
}
