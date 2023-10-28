package org.example.checker;

import org.example.matrix.Coordinate;
import org.example.matrix.CoordinateMatrix;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Writer {

    public static void saveToFile(CoordinateMatrix matrix, String fileName) {
        try {
            System.out.println("Saving...");
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Coordinate coordinate : matrix.coordinates) {
                bufferedWriter.write(coordinate.i() + " " + coordinate.j() + " " + coordinate.value() + "\n");
            }

            bufferedWriter.close();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
