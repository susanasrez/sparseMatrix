package org.example.controller;

import org.example.Matrix;
import org.example.MatrixBuilder;
import org.example.matrixbuilders.MatrixMarketReader;

public class Controller {

    public Controller(String path){
        MatrixBuilder x = new MatrixMarketReader(path);
        ((MatrixMarketReader) x).readMatrixMarketFile();
        Matrix c = x.get();
    }
}
