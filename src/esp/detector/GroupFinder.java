package esp.detector;

import esp.model.Coordinate;
import esp.model.Sector;

import java.util.Arrays;

import static esp.model.CellState.HUMAN;
import static esp.util.LambdaUtils.as;

class GroupFinder {
    private final Sector sector;

    GroupFinder(Sector sector) {
        this.sector = sector;
    }

    Group find(Coordinate coordinate) {
        return find(coordinate, new Group());
    }

    private Group find(Coordinate coordinate, Group group) {
        group.addCell(coordinate);

        Arrays.stream(NeighborDirection.values())
                .map(direction -> direction.getNeighbor(coordinate))
                .filter(as(group::inGroup).negate())
                .filter(coo -> sector.getCell(coo) == HUMAN)
                .peek(group::addCell)
                .forEach(coo -> find(coo, group));
        return group;
    }
}
