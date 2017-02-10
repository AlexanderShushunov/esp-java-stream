package esp.detector;

import esp.model.Coordinate;
import esp.model.Sector;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static esp.model.CellState.HUMAN;

class SectorToHumanCellListConverter {

    List<Coordinate> convert(Sector sector) {
        return IntStream.range(0, sector.getWidth())
                .boxed()
                .flatMap(x ->
                        IntStream.range(0, sector.getHeight()).mapToObj(y -> new Coordinate(x, y))
                ).filter(coo -> sector.getCell(coo) == HUMAN)
                .collect(Collectors.toList());
    }
}
