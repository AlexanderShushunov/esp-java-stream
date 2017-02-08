package esp.detector;

import esp.model.RiskRank;
import esp.model.Sector;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static esp.util.StreamUtils.createStreamBy;

public class RiskRankDetector {

    private final List<RiskRank> ranks;

    public RiskRankDetector(List<RiskRank> ranks) {
        this.ranks = ranks;
    }

    public Map<RiskRank, Long> detect(Sector sector) {

        return createStreamBy(new GroupIterator(sector))
                .map(Group::size)
                .map(this::identify)
                .collect(Collectors.toMap(
                        Function.identity(),
                        smt -> 1L,
                        Long::sum,
                        this::createInitResult));
    }

    private Map<RiskRank, Long> createInitResult() {
        return ranks.stream().collect(Collectors.toMap(Function.identity(), smt -> 0L));
    }

    private RiskRank identify(Integer width) {
        return ranks.stream()
                .filter(rank -> width <= rank.getUpBound())
                .findFirst()
                .orElse(ranks.get(0));
    }
}
