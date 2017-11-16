import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CountryTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityE, cityF, cityG;
    private Map<City, List<Road>> network1, network2;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        game.getRandom().setSeed(0);
        network1 = new HashMap<>();
        network2 = new HashMap<>();

        country1 = new Country("Country 1", network1);
        country2 = new Country("Country 2", network2);
        country1.setGame(game);
        country2.setGame(game);

        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 70, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 100, country2);

        List<Road> roadsA = new ArrayList<>(),
                roadsB = new ArrayList<>(),
                roadsE = new ArrayList<>(),
                roadsF = new ArrayList<>(),
                roadsG = new ArrayList<>();

        network1.put(cityA, roadsA);
        network1.put(cityB, roadsB);
        network1.put(cityC, null);
        network2.put(cityE, roadsE);
        network2.put(cityF, roadsF);
        network2.put(cityG, roadsG);

        country2.addRoads(cityG, cityF, 4);
    }

    @Test
    public void constructor() {
        assertEquals(country1.getName(), "Country 1");
        assertEquals(country1.getNetwork(), network1);

    }

    @Test
    public void reset() throws Exception {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        int valueB = cityE.getValue();
        country1.reset();
        assertEquals(cityA.getValue(),80);
        assertEquals(cityE.getValue(), valueB);
    }

    @Test
    public void bonus() throws Exception {
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

    @Test
    public void addRoads() throws Exception {
        List<Road> networkNew = network1.get(cityA);
        networkNew.add(new Road(cityA, cityB, 6));
        networkNew.add(new Road(cityB, cityA, 6));
        country1.addRoads(cityA, cityB, 6);
        assertEquals(country1.getRoads(cityA),networkNew);

        networkNew.add(new Road(cityA, cityE, 6));
        country1.addRoads(cityA, cityE, 6);
        assertEquals(country1.getRoads(cityA), networkNew);

        networkNew.add(new Road(cityA, cityF, 4));
        country1.addRoads(cityF, cityA, 4);
        assertEquals(country1.getRoads(cityA),networkNew);

        country1.addRoads(cityG, cityF, 4);
        assertEquals(country1.getRoads(cityA),networkNew);
    }

    @Test
    public void position() throws Exception {
        assertEquals(country1.position(cityA).getFrom(), cityA);
        assertEquals(country1.position(cityA).getTo(), cityA);
        assertEquals(country1.position(cityA).getDistance(), 0);
    }

    @Test
    public void readyToTravel() throws Exception {
        assertEquals(country1.readyToTravel(cityA, cityA).getFrom(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityA).getTo(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityA).getDistance(), 0);
        assertEquals(country2.readyToTravel(cityG, cityF).getFrom(), cityG);
        assertEquals(country2.readyToTravel(cityG, cityF).getTo(), cityF);
        assertEquals(country2.readyToTravel(cityG, cityF).getDistance(), 4);
    }

    @Test
    public void getRoads() throws Exception {
        assertEquals(country1.getRoads(cityA), network1.get(cityA));
        assertEquals(country1.getRoads(cityC), network1.get(cityC));
    }

    @Test
    public void getCity() throws Exception {
        assertEquals(country1.getCity("City A"),cityA);
    }

    @Test
    public void getCities() throws Exception {
        List<City> cities = new ArrayList<>();
        cities.add(cityA); cities.add(cityB); cities.add(cityC);
        assertEquals(country1.getCities(),cities);
    }

}


