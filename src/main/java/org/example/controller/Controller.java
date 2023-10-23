package org.example.controller;

import org.example.Matrix;
import org.example.MatrixOperations;
import org.example.MatrixTransformations;

import org.example.matrix.CompressorCCSMatrix;
import org.example.matrix.CompressorCRSMatrix;
import org.example.matrix.CoordinateMatrix;
import org.example.matrix.DenseMatrix;
import org.example.matrixbuilders.*;


public class Controller {

    public Controller(String path){
        CoordinateMatrix x = new CoordinateMatrixGenerator().generateRandomCoordinateMatrix(64, 0.2);
        CompressorCRSMatrix cr = new MatrixTransformations().transformCOO_CRS(x);
        CompressorCCSMatrix cs = new MatrixTransformations().transformCOO_CCS(x);

        long startTime = System.currentTimeMillis();
        Matrix x1 = new MatrixOperations().multiply(cr, cs);
        long endTime = System.currentTimeMillis();
        long tiempoTranscurrido = endTime - startTime;

        System.out.println("La multiplicacion sparse tomó " + tiempoTranscurrido + " milisegundos.");

        DenseMatrix d1 = new MatrixTransformations().transform(x);

        long startTime2 = System.currentTimeMillis();
        Matrix x2 = new MatrixOperations().multiply(d1, d1);
        long endTime2 = System.currentTimeMillis();
        long tiempoTranscurrido2 = endTime2 - startTime2;
        System.out.println("La multiplicacion dense tomó " + tiempoTranscurrido2 + " milisegundos.");

    }










}
