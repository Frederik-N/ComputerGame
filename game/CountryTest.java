import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class CountryTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
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
        Country countryTest = new Country("Test Country", network1);
        assertEquals(countryTest.getName(), "Test Country");
        assertEquals(countryTest.getNetwork(), network1);
    }

    @Test
    public void reset() throws Exception {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        int valueE = cityE.getValue();
        country1.reset();
        int valueB = cityE.getValue();
        assertEquals(cityA.getValue(),80);
        assertEquals(cityE.getValue(), valueE);
    }

    @Test
    public void bonus() throws Exception {
        for(int seed = 0; seed<1000; seed++) {
            game.getRandom().setSeed(seed);
            int sum = 0;
            int sum1 = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> values1 = new HashSet<>();
            for(int i = 0; i<10000; i++) {
                int bonus = country1.bonus(80);
                int bonus1 = country1.bonus(1);
                assertEquals(country1.bonus(0), 0);
                assertTrue(0<=bonus && bonus<=80);
                assertTrue(0<=bonus1 && bonus1<=1);
                sum += bonus;
                sum1 += bonus1;
                values.add(bonus);
                values1.add(bonus1);
            }
            assertTrue(375000< sum && sum < 475000);
            assertEquals(values.size(),81);
            assertTrue(4000 < sum1 && sum1 < 6000);
            assertEquals(values1.size(), 2);
        }
    }

    @Test
    public void addRoads() throws Exception {
        List<Road> networkA = network1.get(cityA);
        List<Road> networkB = network1.get(cityB);
        List<Road> networkF = network2.get(cityF);
        List<Road> networkG = network2.get(cityG);
        List<Road> networkE = network2.get(cityE);

        /** Two cities within the same country    */
        networkA.add(new Road(cityA, cityB, 6));
        networkB.add(new Road(cityB, cityA, 6));
        country1.addRoads(cityA, cityB, 6);
        assertEquals(country1.getRoads(cityA),networkA);
        assertEquals(country1.getRoads(cityB),networkB);

        /** Two cities in different countries    */
        networkA.add(new Road(cityA, cityE, 6));
        country1.addRoads(cityA, cityE, 6);
        assertEquals(country1.getRoads(cityA), networkA);
        assertEquals(country2.getRoads(cityE), networkE);

        /** Two cities not in the country    */
        country1.addRoads(cityG, cityF, 4);
        assertEquals(country2.getRoads(cityF),networkF);
        assertEquals(country2.getRoads(cityG),networkG);
    }

    @Test
    public void position() throws Exception {
        /** A city in the country    */
        assertEquals(country1.position(cityA).getFrom(), cityA);
        assertEquals(country1.position(cityA).getTo(), cityA);
        assertEquals(country1.position(cityA).getDistance(), 0);

        /** A city not in the country   */
        assertEquals(country1.position(cityE).getFrom(), cityE);
        assertEquals(country1.position(cityE).getTo(), cityE);
        assertEquals(country1.position(cityE).getDistance(), 0);
    }

    @Test
    public void readyToTravel() throws Exception {
        /** ??    */
        assertEquals(country1.readyToTravel(cityA, cityA).getFrom(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityA).getTo(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityA).getDistance(), 0);

        /** ??    */
        assertEquals(country2.readyToTravel(cityG, cityF).getFrom(), cityG);
        assertEquals(country2.readyToTravel(cityG, cityF).getTo(), cityF);
        assertEquals(country2.readyToTravel(cityG, cityF).getDistance(), 6);

        /** ??    */
        assertEquals(country1.readyToTravel(cityB, cityC).getFrom(), cityB);
        assertEquals(country1.readyToTravel(cityB, cityC).getTo(), cityB);
        assertEquals(country1.readyToTravel(cityB, cityC).getDistance(), 0);

        /** ??    */
        assertEquals(country1.readyToTravel(cityE, cityA).getFrom(), cityE);
        assertEquals(country1.readyToTravel(cityE, cityA).getTo(), cityE);
        assertEquals(country1.readyToTravel(cityE, cityA).getDistance(), 0);
    }

    @Test
    public void getRoads() throws Exception {
        /** A city in the country    */
        assertEquals(country2.getRoads(cityG),network2.get(cityG));

        /** A city not in the country    */
        assertEquals(country1.getRoads(cityF), Collections.emptyList());
    }

    @Test
    public void getCity() throws Exception {
        /** A city in the country    */
        assertEquals(country1.getCity("City A"), cityA);

        /** A city in another country    */
        assertEquals(country1.getCity("City E"), null);
    }

    @Test
    public void getCities() throws Exception {
        List<City> cities = new ArrayList<>();
        cities.add(cityA); cities.add(cityB); cities.add(cityC); cities.add(cityD);
        assertEquals(country1.getCities(), cities);
    }

}


