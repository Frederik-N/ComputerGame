import java.util.*;

public class Country {
    private String name;
    private Map<City,List<Road>> network;

    public Country(String name, Map<City,List<Road>> network) {
        this.name = name;
        this.network = network;
    }

    public String getName() {
        return name;
    }

    public Map<City,List<Road>> getNetwork() {
        return network;
    }

    public List<Road> getRoads(City c) {
        return network.get(c);
    }

    public List<City> getCities() {
        List cities = new ArrayList<>(network.keySet());
        Collections.sort(cities);
        return cities;

    }

    public City getCity(String name) {
        for(City c : getCities()) {
            if(c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public void reset() {
        for(City c: getCities()) {
            c.reset();
        }
    }

    public int bonus(int value) {
        if(value>0) {
        Random rand = new Random();
        return rand.nextInt(value);
        }
        return 0;
    }

    public void addRoads(City a, City b, int length) {
        if (network.containsKey(a) && network.containsKey(b)) {
            network.get(a).add(new Road(a, b, length));
            network.get(b).add(new Road(b, a, length));
        } else if(network.containsKey(a)) {
            network.get(a).add(new Road(a, b, length));
        } else if(network.containsKey(b)){
            network.get(b).add(new Road(b, a, length));
        }
    }

    public Position position(City city) {
        return new Position(city, city, 0);
    }

    public Position readyToTravel(City from, City to) {
        if(!from.equals(to) || from.getCountry().equals(name)) {
            for(Road r : network.get(from)) {
                if(r.getTo().equals(to)) {
                    return new Position(from, to, r.getLength());
                }
            }
        }
        return position(from);
    }
}
