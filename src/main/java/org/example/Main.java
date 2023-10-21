package org.example;

import org.example.controller.Controller;

public class Main {
    public static void main(String[] args) {
        String path = "/resources/b1_ss.mtx";
        String dir = System.getProperty("user.dir");
        String filepath = dir + path;
        new Controller(filepath);
    }
}