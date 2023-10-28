package org.example;

import org.example.testSuite.TimeMarkBiggestMatrix;
import org.example.testSuite.TimeMarkStateSparse;

public class Controller {

    public Controller(String matrxi){
        new TimeMarkStateSparse.Operands().setup();
        new TimeMarkBiggestMatrix.Operands(matrxi).setup();
    }
}
