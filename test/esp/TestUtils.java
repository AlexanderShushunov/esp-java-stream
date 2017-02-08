package esp;

import esp.model.CellState;
import esp.model.Coordinate;
import esp.model.Sector;

public final class TestUtils {

    public static Coordinate coo(int x, int y) {
        return new Coordinate(x, y);
    }

    public static Sector create(String str) {
        String[] rows = str.split("\\n");
        Sector ret = new Sector(rows[0].length(), rows.length);
        for (int y = 0; y < rows.length; y++) {
            for (int x = 0; x < rows[0].length(); x++) {
                ret.setCell(coo(x, y), charToCellState(rows[y].charAt(x)));
            }
        }
        return ret;

    }

    private static CellState charToCellState(char symbol) {
        if (symbol == 'x') {
            return CellState.HUMAN;
        } else {
            return CellState.EMPTY;
        }
    }
}
