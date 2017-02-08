package esp.detector;

import esp.model.Coordinate;

import java.util.HashSet;
import java.util.Set;

class Group {
    private Set<Coordinate> cells = new HashSet<>();

    void addCell(Coordinate coordinate) {
        cells.add(coordinate);
    }

    boolean inGroup(Coordinate coordinate) {
        return cells.contains(coordinate);
    }

    int size() {
        return cells.size();
    }
}
