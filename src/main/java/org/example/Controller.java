package org.example;

import org.example.testSuite.TimeMarkBiggestMatrix;
import org.example.testSuite.TimeMarkStateSparse;

public class Controller {

    public Controller(String matrix){
        new TimeMarkStateSparse.Operands().setup();
        new TimeMarkBiggestMatrix.Operands(matrix).setup();
    }
}
