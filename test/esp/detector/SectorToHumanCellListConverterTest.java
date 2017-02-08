package esp.detector;

import esp.model.Coordinate;
import esp.model.Sector;
import org.junit.Test;

import java.util.List;

import static esp.TestUtils.coo;
import static esp.TestUtils.create;
import static org.junit.Assert.assertTrue;

public class SectorToHumanCellListConverterTest {
    private SectorToHumanCellListConverter converter = new SectorToHumanCellListConverter();

    @Test
    public void convertEmptySector() throws Exception {
        // @formatter:off
            Sector sector = create("   \n" +
                                   "   ");
            // @formatter:on
        assertTrue(converter.convert(sector).isEmpty());
    }

    @Test
    public void convertForHumansSector() throws Exception {
        // @formatter:off
        Sector sector = create(" x   \n" +
                               "xx   \n" +
                               "     \n" +
                               "    x");
        // @formatter:on
        List<Coordinate> res = converter.convert(sector);
        assertTrue(res.contains(coo(1, 0)));
        assertTrue(res.contains(coo(0, 1)));
        assertTrue(res.contains(coo(1, 1)));
        assertTrue(res.contains(coo(4, 3)));
    }
}