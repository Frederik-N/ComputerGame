import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MafiaCountryTest {
    private Game game;
    private Country country1;
    private MafiaCountry country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        game.getRandom().setSeed(0);
        Map<City, List<Road>> network1 = new HashMap<>();
        Map<City, List<Road>> network2 = new HashMap<>();

        country1 = new Country("Country 1", network1);
        country2 = new MafiaCountry("Country 2", network2);
        country1.setGame(game);
        country2.setGame(game);

        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 70, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 100, country2);

        List<Road> roadsA = new ArrayList<>(),
                roadsB = new ArrayList<>(),
                roadsC = new ArrayList<>(),
                roadsD = new ArrayList<>(),
                roadsE = new ArrayList<>(),
                roadsF = new ArrayList<>(),
                roadsG = new ArrayList<>();

        network1.put(cityA, roadsA);
        network1.put(cityB, roadsB);
        network1.put(cityC, roadsC);
        network1.put(cityD, roadsD);
        network2.put(cityE, roadsE);
        network2.put(cityF, roadsF);
        network2.put(cityG, roadsG);

        country1.addRoads(cityA, cityB, 4);
        country1.addRoads(cityA, cityC, 3);
        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);
    }
    @Test
    public void constructor() {
        Map<City, List<Road>> networkTest = new HashMap<>();
        MafiaCountry countryTest = new MafiaCountry("Test Country", networkTest);
        assertEquals(countryTest.getName(), "Test Country");
        assertEquals(countryTest.getNetwork(), networkTest);
    }
    @Test
    public void bonus() throws Exception {
        for(int seed = 0; seed<1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            Set<Integer> values = new HashSet<>();
            for(int i = 0; i<50000; i++) {
                int bonus = country2.bonus(80);
                if(bonus < 0) {
                    robs++;
                    assertTrue(10 <= -bonus && -bonus <= 50);
                    loss -= bonus;
                    values.add(-bonus);
                }
            }
            assertTrue( 285000 < loss && loss < 315000);
            assertTrue(9500 < robs && robs < 10500);
            assertEquals(values.size(), 40);
        }
    }
}
