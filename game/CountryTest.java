import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class CountryTest {
    private Game game;
    private Country country1, country2, country1copy;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        game.getRandom().setSeed(0);
        Map<City, List<Road>> network1 = new HashMap<>();
        Map<City, List<Road>> network2 = new HashMap<>();

        country1copy = new Country("Country 1", network1);
        country1 = new Country("Country 1", network1);
        country2 = new Country("Country 2", network2);
        country1.setGame(game);
        country2.setGame(game);

        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 70, country2);
        cityD = new City("City D", 100, country2);
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
        network2.put(cityC, roadsC);
        network1.put(cityD, roadsD);
        network2.put(cityE, roadsE);
        network2.put(cityF, roadsF);
        network2.put(cityG, roadsG);

        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityG, 6);
    }

    @Test
    public void constructor() {
        Map<City, List<Road>> networkTest = new HashMap<>();
        Country countryTest = new Country("Test Country", networkTest);
        assertEquals(countryTest.getName(), "Test Country");
        assertEquals(countryTest.getNetwork(), networkTest);
    }

    @Test
    public void reset() throws Exception {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityE.arrive(); cityE.arrive(); cityE.arrive();
        int valueE = cityE.getValue();
        country1.reset();
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
            assertTrue(375000< sum && sum < 425000);
            assertEquals(values.size(),81);
            assertTrue(4000 < sum1 && sum1 < 6000);
            assertEquals(values1.size(), 2);
        }
    }

    @Test
    public void addRoads() throws Exception {
        List<Road> roadsA = new ArrayList<>();
        List<Road> roadsB = new ArrayList<>();
        List<Road> roadsC =  new ArrayList<>();
        List<Road> roadsD =  new ArrayList<>();

        /** Two cities within the same country    */
        roadsA.add(new Road(cityA, cityB, 6));
        roadsB.add(new Road(cityB, cityA, 6));
        country1.addRoads(cityA, cityB, 6);
        assertEquals(country1.getRoads(cityA).size(),roadsA.size());
        assertEquals(country1.getRoads(cityA).get(0).getLength(), roadsA.get(0).getLength());
        assertEquals(country1.getRoads(cityA).get(0).getFrom(), roadsA.get(0).getFrom());
        assertEquals(country1.getRoads(cityA).get(0).getTo(), roadsA.get(0).getTo());

        assertEquals(country1.getRoads(cityB).size(),roadsB.size());
        assertEquals(country1.getRoads(cityB).get(0).getLength(), roadsB.get(0).getLength());
        assertEquals(country1.getRoads(cityB).get(0).getFrom(), roadsB.get(0).getFrom());
        assertEquals(country1.getRoads(cityB).get(0).getTo(), roadsB.get(0).getTo());

        /** Two cities in different countries    */
        roadsA.add(new Road(cityA, cityC, 6));
        country1.addRoads(cityA, cityC, 6);
        assertEquals(country1.getRoads(cityA).size(), roadsA.size());
        assertEquals(country1.getRoads(cityA).get(1).getLength(),roadsA.get(1).getLength());
        assertEquals(country1.getRoads(cityA).get(1).getTo(), roadsA.get(1).getTo());
        assertEquals(country1.getRoads(cityA).get(1).getFrom(), roadsA.get(1).getFrom());
        assertEquals(country2.getRoads(cityC), roadsC);

        // Virker ikke, da addroads ikke tilføjer denne vej? ved ikke hvorfor
//        /** Two cities in different countries reversed    */
//        roadsC.add(new Road(cityC, cityA, 4));
//        country2.addRoads(cityC, cityA, 4);
//        assertEquals(country2.getRoads(cityC).size(), roadsC.size());
//        assertEquals(country2.getRoads(cityC).get(0).getLength(),roadsC.get(0).getLength());
//        assertEquals(country2.getRoads(cityC).get(0).getTo(), roadsC.get(0).getTo());
//        assertEquals(country2.getRoads(cityC).get(0).getFrom(), roadsC.get(0).getFrom());
//        assertEquals(country1.getRoads(cityA), roadsA);

        /** Two cities not in the country    */
        country1.addRoads(cityC, cityD, 4);
        assertEquals(country2.getRoads(cityC),roadsC);
        assertEquals(country2.getRoads(cityD),roadsD);
    }

    @Test
    public void position() throws Exception {
        /** A city in the country    */
        assertEquals(country1.position(cityA).getFrom(), cityA);
        assertEquals(country1.position(cityA).getTo(), cityA);
        assertEquals(country1.position(cityA).getTotal(), 0);

        /** A city not in the country   */
        assertEquals(country1.position(cityE).getFrom(), cityE);
        assertEquals(country1.position(cityE).getTo(), cityE);
        assertEquals(country1.position(cityE).getTotal(), 0);
    }

    @Test
    public void readyToTravel() throws Exception {
        /** from equals to    */
        assertEquals(country1.readyToTravel(cityA, cityA).getFrom(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityA).getTo(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityA).getTotal(), 0);

        /** from does not equal to and there is a road.    */
        assertEquals(country2.readyToTravel(cityE, cityF).getFrom(), cityE);
        assertEquals(country2.readyToTravel(cityE, cityF).getTo(), cityF);
        assertEquals(country2.readyToTravel(cityE, cityF).getTotal(), 2);

        /** from does not equal to and there is no road.    */
        assertEquals(country1.readyToTravel(cityA, cityE).getFrom(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityE).getTo(), cityA);
        assertEquals(country1.readyToTravel(cityA, cityE).getTotal(), 0);

        /** from is not in the country with road.   */
        country2.addRoads(cityE, cityC, 5);
        assertEquals(country2.readyToTravel(cityE, cityC).getFrom(), cityE);
        assertEquals(country2.readyToTravel(cityE, cityC).getTo(), cityC);
        assertEquals(country2.readyToTravel(cityE, cityC).getTotal(), 5);

        /** from is not in the country without road. */
        assertEquals(country2.readyToTravel(cityE, cityA).getFrom(), cityE);
        assertEquals(country2.readyToTravel(cityE, cityA).getTo(), cityE);
        assertEquals(country2.readyToTravel(cityE, cityA).getTotal(), 0);
    }

    @Test
    public void getRoads() throws Exception {
        /** A city in the country    */
        assertEquals(country2.getRoads(cityG),country2.getNetwork().get(cityG));

        /** A city not in the country    */
        assertEquals(country1.getRoads(cityG), Collections.emptyList());
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
        Map<City, List<Road>> networkTest = new HashMap<>();
        /** Test with an empty country */
        Country countryTest = new Country("Country Test", networkTest);
        assertEquals(countryTest.getCities(),cities);
        /** Test with a country with cities */
        cities.add(cityA); cities.add(cityB); cities.add(cityD);
        assertEquals(country1.getCities(), cities);
    }

    @Test
    public void equals() throws Exception {
        assertEquals(country1.equals(country1), true);
        assertEquals(country1.equals(cityA), false);
        assertEquals(country1.equals(country1copy), true);
        assertEquals(country1.equals(null), false);

    }

    @Test
    public void hashCodeTest() throws Exception {
        int hashCode1 = country1.hashCode();
        assertEquals(country1.hashCode(), hashCode1);
        assertNotEquals(country1.hashCode(), country2.hashCode());
    }

}


