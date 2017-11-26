import java.util.List;
import java.util.Map;
/**
 * A mafiaCountry that contains cities and roads between cities.
 * @author Jonas Madsen || Frederik Nielsen
 * @version 26.11.2017
 */
public class MafiaCountry extends Country {

    /**
     * Creates a new MafiaCountry object.
     *
     * @param name    name of this country.
     * @param network contains cities mapped to a list of roads.
     */
    public MafiaCountry(String name, Map<City, List<Road>> network) {
        super(name, network);
    }

    /**
     * If risk is over the random number rolled from 0 to 99, then returns the loss
     * else gets the random number bonus based on the city current value.
     * @param value the value of the city
     * @return Loss after robbed or the randomly rolled number (0 if value <= 0).
     */
    @Override
    public int bonus(int value) {
        if(getGame().getSettings().getRisk() > getGame().getRandom().nextInt(100)) {
            return -getGame().getLoss();
        }
        return super.bonus(value);
    }
}
