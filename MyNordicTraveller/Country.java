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
        if(network.containsKey(name)) {
            return (City) network.get(name);
        }
        return null;
    }

    public void reset() {
        network.clear();
    }

    public int bonus(int value) {
        if(value>0) {
        Random rand = new Random();
        return rand.nextInt(value);
        }
        return 0;
    }

    public void addRoads(City a, City b, int length) {
        if(!network.containsKey(a) && !network.containsKey(b)) {
            return;
        }
        List<Road> roadsA = network.get(a);
        List<Road> roadsB = network.get(b);
        if (network.containsKey(a) && network.containsKey(b)) {
            roadsA.add(new Road(a, b, length));
            roadsB.add(new Road(b, a, length));
            network.put(a,roadsA);
            network.put(b,roadsB);
            return;
        }
        if(network.containsKey(a)) {
            roadsA.add(new Road(a, b, length));
            network.put(a,roadsA);
            return;
        }
        roadsB.add(new Road(b, a, length));
        network.put(b, roadsB);



    }

    public Position position(City city) {

    }

    public Position readyToTravel(City from, City to) {

    }
}
