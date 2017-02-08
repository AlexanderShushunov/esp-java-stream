package esp.model;

public class Sector {

    private CellState[][] cellsStates;

    public Sector(int width, int height) {
        assert(width > 0 && height > 0);
        cellsStates = new CellState[height][width];
    }

    public int getWidth() {
        return cellsStates[0].length;
    }

    public int getHeight() {
        return cellsStates.length;
    }

    public CellState getCell(Coordinate coordinate) {
        if (!inBound(coordinate)) {
            return CellState.EMPTY;
        }
        return cellsStates[coordinate.getY()][coordinate.getX()];
    }

    public void setCell(Coordinate coordinate, CellState cellState) {
        if (!inBound(coordinate)) {
            return;
        }
        cellsStates[coordinate.getY()][coordinate.getX()] = cellState;
    }

    private boolean inBound(Coordinate coordinate) {
        return coordinate.getX() <  getWidth()
                && coordinate.getY() < getHeight();
    }
}
