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

    }

    public List<City> getCities() {

    }

    public City getCity(String name) {

    }
}
