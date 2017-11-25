import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CapitalCityTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        Map<City, List<Road>> network1 = new HashMap<>();
        Map<City, List<Road>> network2 = new HashMap<>();

        country1 = new Country("Country 1", network1);
        country1.setGame(game);
        country2 = new Country("Country 2", network2);
        country2.setGame(game);

        cityA = new CapitalCity("City A", 80, country1);
        cityB = new CapitalCity("City B", 60, country1);
        cityC = new CapitalCity("City C", 40, country2);
    }

    @Test
    public void arriveFromOtherCountry() throws Exception {
        for (int i=0; i<1000; i++) {
            Player p = new Player(new Position(cityA, cityC, 0), 250);
            game.getRandom().setSeed(i);
            int bonus = country2.bonus(40);
            int toll = ((p.getMoney() * (cityC.getCountry().getGame().getSettings().getTollToBePaid()))/100);
            int consumption = cityC.getCountry().getGame().getRandom().nextInt(p.getMoney()+(bonus-toll)+1);
            game.getRandom().setSeed(i);
            int arrive = cityC.arrive(p);
            assertEquals(arrive, bonus-toll-(consumption));
            assertEquals(cityC.getValue(), 40+(toll-bonus)+(consumption));
            cityC.reset();
        }
    }

    @Test
    public void arrive() throws Exception {
        for (int i=0; i<1000; i++) {
            Player p = new Player(new Position(cityA, cityB, 0), 250);
            game.getRandom().setSeed(i);
            int bonus = country1.bonus(60);
            int consumption = p.getCountry().getGame().getRandom().nextInt(p.getMoney()+(bonus)+1);
            game.getRandom().setSeed(i);
            int arrive = cityB.arrive(p);
            assertEquals(arrive, bonus-consumption);
            assertEquals(cityB.getValue(), 60-bonus+consumption);
            cityB.reset();
        }
    }

}