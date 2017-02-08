package esp.detector;


import esp.model.Sector;
import org.junit.Test;

import static esp.TestUtils.coo;
import static esp.TestUtils.create;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;


public class GroupFinderTest {

    @Test
    public void findOneCellGroup() throws Exception {
        // @formatter:off
        Sector sector = create("   \n" +
                               "x  ");
        // @formatter:on
        GroupFinder groupFinder = new GroupFinder(sector);
        Group res = groupFinder.find(coo(0, 1));
        assertThat(res.size(), is(1));
        assertTrue(res.inGroup(coo(0, 1)));
    }

    @Test
    public void findLineGroup() throws Exception {
        // @formatter:off
        Sector sector = create("   \n" +
                               "xxx\n" +
                               "   \n" +
                               " x ");
        // @formatter:on
        GroupFinder groupFinder = new GroupFinder(sector);
        Group res = groupFinder.find(coo(0, 1));
        assertThat(res.size(), is(3));
        assertTrue(res.inGroup(coo(0, 1)));
        assertTrue(res.inGroup(coo(1, 1)));
        assertTrue(res.inGroup(coo(2, 1)));
    }


    @Test
    public void findComplicatedGroup() throws Exception {
        // @formatter:off
        Sector sector = create("   x  x\n" +
                               "xxx   x\n" +
                               "  x   x\n" +
                               "  xxxxx\n" +
                               "xx  x  \n" +
                               "  xxx  \n" +
                               " x   x ");
        // @formatter:on
        GroupFinder groupFinder = new GroupFinder(sector);
        Group res = groupFinder.find(coo(3, 3));
        assertThat(res.size(), is(16));
        assertTrue(res.inGroup(coo(0, 1)));
        assertTrue(res.inGroup(coo(6, 0)));
        assertTrue(res.inGroup(coo(2, 5)));
    }


}