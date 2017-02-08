package esp.detector;

import esp.model.Coordinate;

enum NeighborDirection {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    private final int xOffset;
    private final int yOffset;

    NeighborDirection(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public Coordinate getNeighbor(Coordinate in) {
        return new Coordinate(in.getX() + xOffset, in.getY() + yOffset);
    }
}
