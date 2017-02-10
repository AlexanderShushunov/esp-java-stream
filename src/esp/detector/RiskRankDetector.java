package esp.detector;

import esp.model.Coordinate;
import esp.model.RiskRank;
import esp.model.Sector;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static esp.model.CellState.HUMAN;

public class RiskRankDetector {

    private final List<RiskRank> ranks;

    public RiskRankDetector(List<RiskRank> ranks) {
        this.ranks = ranks;
    }

    public Map<RiskRank, Long> detect(Sector sector) {
        GroupFinder groupFinder = new GroupFinder(sector);
        return allCoordinatesInRect(sector.getWidth(), sector.getHeight())
                .filter(coo -> sector.getCell(coo) == HUMAN)
                .map(groupFinder::find)
                .distinct()
                .map(Group::size)
                .map(this::groupSizeToRank)
                .collect(Collectors.toMap(
                        Function.identity(),
                        smt -> 1L,
                        Long::sum,
                        this::createInitResult));
    }

    static Stream<Coordinate> allCoordinatesInRect(int width, int height) {
        return IntStream.range(0, width * height)
                .mapToObj(n -> new Coordinate(n % width, n / width));
    }

    private RiskRank groupSizeToRank(Integer size) {
        return ranks.stream()
                .filter(rank -> size <= rank.getUpBound())
                .sorted(Comparator.comparingInt(RiskRank::getUpBound))
                .findFirst()
                .orElse(ranks.get(0));
    }

    private Map<RiskRank, Long> createInitResult() {
        return ranks.stream().collect(Collectors.toMap(Function.identity(), smt -> 0L));
    }
}
