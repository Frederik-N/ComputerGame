import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {
    private Country country1;
    private City cityA, cityB;
    private Position pos;

    @Before
    public void setUp() throws Exception {
        country1 = new Country("Country 1", null);
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 70, country1);
        pos = new Position(cityA, cityB, 3);
    }

    @Test
    public void constructor() {
        assertEquals(pos.getFrom(), cityA);
        assertEquals(pos.getTo(),cityB);
        assertEquals(pos.getDistance(), pos.getDistance());
        assertEquals(pos.getTotal(), pos.getTotal());
    }

    @Test
    public void hasArrived() throws Exception {
        assertFalse(pos.hasArrived());
        pos.move();
        assertFalse(pos.hasArrived());
        pos.move();
        assertFalse(pos.hasArrived());
        pos.move();
        assertTrue(pos.hasArrived());
    }

    @Test
    public void move() {
        assertEquals(pos.getDistance(),3);
        assertTrue(pos.move());
        assertEquals(pos.getDistance(),2);
        assertTrue(pos.move());
        assertEquals(pos.getDistance(),1);
        assertTrue(pos.move());
        assertEquals(pos.getDistance(),0);
    }

    @Test
    public void turnAround() {
        pos.move();
        pos.turnAround();
        assertEquals(pos.getFrom(),cityB);
        assertEquals(pos.getTo(), cityA);
        assertEquals(pos.getDistance(), 1);
        assertEquals(pos.getTotal(), 3);
        pos.move();
        pos.turnAround();
        assertEquals(pos.getFrom(), cityA);
        assertEquals(pos.getTo(), cityB);
        assertEquals(pos.getDistance(), 3);
        assertEquals(pos.getTotal(), 3);
        pos.turnAround();
        assertEquals(pos.getFrom(),cityB);
        assertEquals(pos.getTo(),cityA);
        assertEquals(pos.getDistance(), 0);
        assertEquals(pos.getTotal(), 3);
    }
}
