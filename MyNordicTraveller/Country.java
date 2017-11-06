import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
}
