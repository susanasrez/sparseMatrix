package org.example;

import org.example.testSuite.TimeMarkStateSparse;

public class Main {
    public static void main(String[] args) {
        String path = "b1_ss.mtx";
        String dir = System.getProperty("user.dir");
        String filepath = dir + path;

        //new Controller(path);
        new TimeMarkStateSparse.Operands().setup();
    }
}