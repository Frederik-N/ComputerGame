import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CountryTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        game.getRandom().setSeed(0);
        Map<City, List<Road>> network1 = new HashMap<>();
        Map<City, List<Road>> network2 = new HashMap<>();

        // Create countries
        country1 = new Country("Country 1", network1);
        country2 = new Country("Country 2", network2);
        country1.setGame(game);
        country2.setGame(game);

        // Create Cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Create road lists
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

        // Create roads
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
        assertEquals(country1.getName(), "Country 1");
    }

    @Test
    public void reset() {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityB.arrive(); cityE.arrive(); cityE.arrive();
        int valueB = cityE.getValue();
        country1.reset();
        assertEquals(cityA.getValue(),80);
        assertEquals(cityE.getValue(), valueB);
    }

    @Test
    public void bonus() {
        for(int seed = 0; seed<1000; seed++) {
            game.getRandom().setSeed(seed);
            int sum = 0;
            int sum1 = 0;
            int sum0 = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> values1 = new HashSet<>();
            Set<Integer> values0 = new HashSet<>();
            for(int i = 0; i<10000; i++) {
                int bonus = country1.bonus(80);
                int bonus1 = country1.bonus(1);
                int bonus0 = country1.bonus(0);
                assertTrue(0<=bonus && bonus<=80);
                assertTrue(0<=bonus1 && bonus1<=1);
                assertTrue(bonus0==0);
                sum += bonus;
                sum1 += bonus1;
                sum0 += bonus0;
                values.add(bonus);
                values1.add(bonus1);
                values0.add(bonus0);
            }
            assertTrue(350000< sum && sum < 450000);
            assertEquals(values.size(),81);
            assertTrue(4000< sum1 && sum1 < 6000);
            assertEquals(values1.size(), 2);
            assertTrue(sum0==0);
            assertEquals(values0.size(), 1);
        }
    }
}


