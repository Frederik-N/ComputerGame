import java.util.List;
import java.util.Map;
import java.util.Random;

public class MafiaCountry extends Country {


    /**
     * Creates a new Country object.
     *
     * @param name    name of this country.
     * @param network contains cities mapped to a list a roads.
     */
    public MafiaCountry(String name, Map<City, List<Road>> network) {
        super(name, network);
    }

    @Override
    public int bonus(int value) {
        if(getGame().getSettings().getRisk() > getGame().getRandom().nextInt(100)) {
            return -getGame().getLoss();
        }
        return super.bonus(value);
    }
}
