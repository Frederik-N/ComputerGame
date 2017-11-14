import java.util.*;

public class Country {
    private String name;
    private Map<City,List<Road>> network;
    private Game game;

    public Country(String name, Map<City,List<Road>> network) {
        this.name = name;
        this.network = network;
    }

    /**
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * @return the network of the cities and roads in the country
     */
    public Map<City,List<Road>> getNetwork() {
        return network;
    }

    /**
     * @param c specify a city in the country
     * @return the roads of a specific city in the country
     */
    public List<Road> getRoads(City c) {
        if(network.containsKey(c)) {
            return network.get(c);
        }
        return Collections.emptyList();
    }

    /**
     * @return an list of cities in the country
     */
    public List<City> getCities() {
        List cities = new ArrayList<>(network.keySet());
        Collections.sort(cities);
        return cities;

    }

    /**
     * Checks for the city in the country
     * @param name a name of an city
     * @return the city if it exist else null
     */
    public City getCity(String name) {
        for(City c : getCities()) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Resets all the cities in the country to their original value
     */
    public void reset() {
        for(City c: getCities()) {
            c.reset();
        }
    }

    /**
     * Gets the random number bonus based on the city current value
     * @param value the value of the city
     * @return the randomly rolled number
     */
    public int bonus(int value) {
        if(value>0) { 
            return game.getRandom().nextInt(value+1);
        }
        return 0;
    }

    /**
     * Adds a road from one city to another one in this country with a specific length.
     * @param a a city in the country
     * @param b another city in the country
     * @param length the length of the road
     */
    public void addRoads(City a, City b, int length) {

        if (network.containsKey(a)) {
            network.get(a).add(new Road(a, b, length));
        }
        if (network.containsKey(b)) {
            network.get(b).add(new Road(b, a, length));
        }
    }

    /**
     * @param city city from the country
     * @return the current position of the player in the this city.
     */
    public Position position(City city) {
        return new Position(city, city, 0);
    }

    /**
     * Checks to see if the player is moving to a "correct" city
     * @param from city that is being moved from
     * @param to city that is being moved to
     * @return position that the player is currently doing
     */
    public Position readyToTravel(City from, City to) {
        if(!from.equals(to)) {
            for(Road r : getRoads(from)) {
                if(r.getTo().equals(to)) {
                    return new Position(from, to, r.getLength());
                }
            }
        }
        return position(from);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
