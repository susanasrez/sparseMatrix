package org.example.controller;


import org.example.model.MatrixMarketReader;

import java.util.ArrayList;
import java.util.Arrays;

public class Controller {

    public Controller(String path){

        //MatrixMarketReader matrixMarketReader = new MatrixMarketReader(path);
        //matrixMarketReader.readMatrixMarketFile();
        //matrixMarketReader.showMatrix();
        ArrayList<Integer> cooRows = new ArrayList<>(Arrays.asList(1, 1, 1, 2 , 3, 3));
        ArrayList<Integer> cooColumns = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 2, 3));
        ArrayList<Double> cooValues = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 5.0, 8.0, 9.0));


        CompressorCRS compressor = new CompressorCRS(cooRows, cooColumns,cooValues,
                3, 3);
        compressor.printCRS();


    }
}
