package esp.detector;

import esp.model.RiskRank;
import esp.model.Sector;

import java.util.Map;

import static esp.TestUtils.create;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RiskRankDetectorTest {


    private static final RiskRank NONE = new RiskRank(2, "NONE");
    private static final RiskRank MINOR = new RiskRank(4, "MINOR");
    private static final RiskRank NORMAL = new RiskRank(7, "NORMAL");
    private static final RiskRank MAJOR = new RiskRank(13, "MAJOR");
    private static final RiskRank CRITICAL = new RiskRank(Integer.MAX_VALUE, "CRITICAL");

    private static final RiskRankDetector STD_RISK_RANK_DETECTOR = new RiskRankDetector(asList(
            NONE,
            MINOR,
            NORMAL,
            MAJOR,
            CRITICAL));

    @org.junit.Test
    public void detectStdComplex() throws Exception {
        // @formatter:off
        Sector sector = create(
                       " x x xxx    \n"+
                       " x   xxx  x \n"+
                       "  x  xxx  x \n"+
                       "xx x      x \n"+
                       "xx xxxxx  x \n"+
                       "x   x  x  x \n"+
                       "x   xxxx    \n"+
                       "  xxx  x    ");
        // @formatter:on
        Map<RiskRank, Long> result = STD_RISK_RANK_DETECTOR.detect(sector);
        assertThat(result.get(NONE), is(3L));
        assertThat(result.get(MINOR), is(0L));
        assertThat(result.get(NORMAL), is(2L));
        assertThat(result.get(MAJOR), is(1L));
        assertThat(result.get(CRITICAL), is(1L));
    }

    @org.junit.Test
    public void detectEmpty() throws Exception {
        // @formatter:off
        Sector sector = create(
                       "      \n"+
                       "      \n"+
                       "      \n");
        // @formatter:on
        Map<RiskRank, Long> result = STD_RISK_RANK_DETECTOR.detect(sector);
        assertThat(result.values().stream().mapToLong(Long::longValue).sum(), is(0L));
    }

    @org.junit.Test
    public void detectBoundaries() throws Exception {
        // @formatter:off
        Sector sector = create(
                       "xx xx xxxx xxxx \n"+
                       "xx xx xxx  xxxx \n"+
                       "   x  xxx  xxx  \n");
        // @formatter:on
        RiskRank first = new RiskRank(4, "NONE");
        RiskRank second = new RiskRank(10, "MINOR");
        RiskRank third = new RiskRank(Integer.MAX_VALUE, "NORMAL");
        Map<RiskRank, Long> result = new RiskRankDetector(asList(first, second, third)).detect(sector);
        assertThat(result.get(second), is(2L));
    }
}