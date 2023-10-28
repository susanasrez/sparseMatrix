package org.example.matrix;

import org.example.Matrix;

import java.util.Comparator;
import java.util.List;

public class CoordinateMatrix extends SparseMatrix{

    public final int size;
    public final List<Coordinate> coordinates;

    public CoordinateMatrix(int size, List<Coordinate> coordinates) {
        this.size = size;
        this.coordinates = coordinates;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double get(int i, int j) {
        return coordinates.stream()
                .filter(c->c.i() == i & c.j() == j)
                .findFirst()
                .map(Coordinate::value)
                .orElse(0.0);
    }

    public Matrix getByRows() {
        coordinates.sort(new CoordinateComparator());
        return new CoordinateMatrix(size, coordinates);
    }

    public Matrix getSortCol() {
        coordinates.sort(new CoordinateComparatorByJ());
        return new CoordinateMatrix(size, coordinates);
    }

    private static class CoordinateComparator implements Comparator<Coordinate> {
        @Override
        public int compare(Coordinate c1, Coordinate c2) {
            return Integer.compare(c1.i(), c2.i());
        }
    }

    private static class CoordinateComparatorByJ implements Comparator<Coordinate> {
        @Override
        public int compare(Coordinate c1, Coordinate c2) {
            return Integer.compare(c1.j(), c2.j());
        }
    }
}
