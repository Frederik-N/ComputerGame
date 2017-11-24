import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class CityTest {
    private Game game;
    private Country country1;
    private MafiaCountry country2;
    private City cityA, cityA2, cityB, cityC, cityD;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        Map<City, List<Road>> network1 = new HashMap<>();
        Map<City, List<Road>> network2 = new HashMap<>();

        country1 = new Country("Country 1", network1);
        country2 = new MafiaCountry("Country 2", network2);
        country2.setGame(game);
        country1.setGame(game);

        cityA = new City("City A", 80, country1);
        cityA2 = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("city D", 30, country2);
    }

    @Test
    public void constructor() {
        City city = new City("CityA", 10, country1);
        assertEquals(city.getName(), "CityA");
        assertEquals(city.getValue(), 10);
        assertEquals(city.getCountry(), country1);
        city.reset();
        assertEquals(city.getValue(), 10);
    }

    @Test
    public void changeValue() throws Exception {
        cityC.changeValue(10);
        assertEquals(cityC.getValue(), 50);
        cityC.changeValue(-10);
        assertEquals(cityC.getValue(), 40);
        cityC.changeValue(-45);
        assertEquals(cityC.getValue(), -5);
    }

    @Test
    public void reset() throws Exception {
        cityC.reset();
        assertEquals(cityC.getValue(), 40);
        cityC.changeValue(100);
        cityC.reset();
        assertEquals(cityC.getValue(), 40);
    }

    @Test
    public void compareTo() throws Exception {
        assertTrue(cityA.compareTo(cityB) < 0);
        assertTrue(cityC.compareTo(cityA) > 0);
        assertEquals(cityA.compareTo(cityA), 0);
    }

    @Test
    public void arrive() throws Exception {
        for (int i=0; i<1000; i++) {
            game.getRandom().setSeed(i);
            int bonus = country2.bonus(30);
            game.getRandom().setSeed(i);
            int arrive = cityD.arrive();
            assertEquals(arrive, bonus);
            if(bonus>0) {
                assertEquals(arrive, bonus);
                assertEquals(cityD.getValue(), 30 - bonus);
            }
            else {
                assertEquals(arrive, bonus);
                assertEquals(cityD.getValue(), 30);
            }
            cityD.reset();
        }
    }

    @Test
    public void equals() throws Exception {
        assertEquals(cityA.equals(cityA2), true);
        assertEquals(cityA.equals(cityB), false);
        assertEquals(cityA.equals(null), false);
    }

    @Test
    public void hashCodeTest() throws Exception {
        assertEquals(cityA.hashCode(), 11*cityA.getName().hashCode() + 13* cityA.getCountry().hashCode());
    }
}