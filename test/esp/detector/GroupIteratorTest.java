package esp.detector;

import esp.model.Sector;
import org.junit.Test;

import static esp.TestUtils.coo;
import static esp.TestUtils.create;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GroupIteratorTest {

    @Test
    public void hasNextEmptySector() throws Exception {
        // @formatter:off
            Sector sector = create("   \n" +
                                   "   ");
        // @formatter:on
        GroupIterator iterator = new GroupIterator(sector);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void hasNextTreeGroupsSector() throws Exception {
        // @formatter:off
            Sector sector = create(" x    \n" +
                                   "    x \n" +
                                   "x     \n" +
                                   "xx    ");
        // @formatter:on
        GroupIterator iterator = new GroupIterator(sector);
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void next() throws Exception {
        // @formatter:off
            Sector sector = create(" xx   \n" +
                                   " xx   \n" +
                                   "   xx \n" +
                                   "xxxx  ");
        // @formatter:on
        GroupIterator iterator = new GroupIterator(sector);
        Group first = iterator.next();
        assertThat(first.size(), is(6));
        assertTrue(first.inGroup(coo(3, 3)));

        Group second = iterator.next();
        assertThat(second.size(), is(4));
        assertTrue(second.inGroup(coo(1, 1)));

    }

}