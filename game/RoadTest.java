import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoadTest {
    private Country country1;
    private City cityA, cityB, cityC;

    @Before
    public void setUp() throws Exception {
        country1 = new Country("Country 1", null);

        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 70, country1);
        cityC = new City("City C", 60, country1);
    }

    @Test
    public void constructor() {
        Road road = new Road(cityA, cityB, 5);
        assertEquals(road.getFrom(), cityA);
        assertEquals(road.getTo(), cityB);
        assertEquals(road.getLength(), 5);

        Road road2 = new Road(cityB, cityA, 3);
        assertEquals(road2.getFrom(), cityB);
        assertEquals(road2.getTo(), cityA);
        assertEquals(road2.getLength(), 3);
    }

    @Test
    public void compareTo() throws Exception {
        Road road1 = new Road(cityA, cityB, 0);
        Road road2 = new Road(cityA, cityC, 0);
        Road road3 = new Road(cityB, cityA, 0);
        assertTrue(road1.compareTo(road2) < 0);
        assertTrue(road2.compareTo(road3) < 0);
        assertTrue(road1.compareTo(road3) < 0);
        assertEquals(road1.compareTo(road1), 0);
    }

}