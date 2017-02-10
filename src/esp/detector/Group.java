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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return cells.equals(group.cells);
    }

    @Override
    public int hashCode() {
        return cells.hashCode();
    }
}
