import java.util.*;
/**
 * A country that contains cities and roads between cities.
 * @author Jonas Madsen || Frederik Nielsen
 * @version 18.11.2017
 */
public class Country {
    /** name of this country */
    private String name;

    /** network contains every city mapped to an element of the type <List<Road>> */
    private Map<City,List<Road>> network;

    /** This game */
    private Game game;

    /**
     * Creates a new Country object.
     * @param name name of this country.
     * @param network contains cities mapped to a list a roads.
     */
    public Country(String name, Map<City,List<Road>> network) {
        this.name = name;
        this.network = network;
    }

    /**
     * Returns name of this country.
     * @return name of this country
     */
    public String getName() {
        return name;
    }

    /**
     * Retuns the network of the cities and roads in this country
     * @return network of cities and roads in this country.
     */
    public Map<City,List<Road>> getNetwork() {
        return network;
    }

    /**
     * Returns the roads that is in a specific city in this country.
     * @param c specify a city in this country
     * @return the roads of a specific city in this country
     */
    public List<Road> getRoads(City c) {
        if(network.containsKey(c)) {
            return network.get(c);
        }
        return Collections.emptyList();
    }

    /**
     * Retuns the cities in this country in a sorted list.
     * @return an list of cities in this country.
     */
    public List<City> getCities() {
        List cities = new ArrayList<>(network.keySet());
        Collections.sort(cities);
        return cities;

    }

    /**
     * Checks for the city is in this country
     * @param name a name of a city
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
     * Resets all the cities in this country to their original value
     */
    public void reset() {
        for(City c: getCities()) {
            c.reset();
        }
    }

    /**
     * Gets the random number bonus based on the city current value
     * @param value the value of the city
     * @return the randomly rolled number or 0 if value <= 0.
     */
    public int bonus(int value) {
        if(value>0) { 
            return game.getRandom().nextInt(value+1);
        }
        return 0;
    }

    /**
     * Adds a road from one city to another one with a specific length depending on which country they are in.
     * @param a a city in this country
     * @param b another city in this country
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
     * Retuns the current position of the player in the city.
     * @param city city
     * @return the current position of the player in the city.
     */
    public Position position(City city) {
        return new Position(city, city, 0);
    }

    /**
     * Checks to see if the player is moving to a "correct" city
     * @param from city that is being moved from
     * @param to city that is being moved to
     * @return position that the player is currently having.
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

    /**
     * Returns the current game.
     * @return the current game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Changes this game to a specific game.
     * @param game a specific game.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Indicates whether an other country is equal to this one
     * @param o the country with which to compare
     * @return true if the countries are the same else false
     */
    public boolean equals(Object o) {
        if(this == o) {return true;}
        if(o == null) {return false;}
        if(getClass() != o.getClass()) {return false;}
        Country other = (Country) o;
        return this.name.equals(other.name);
    }

    /**
     * Returns a hash code value for the country
     * @return a hash code value for the country
     */
    public int hashCode() {
        return 11 * name.hashCode();
    }
}