import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MafiaCountryTest {
    private Game game;
    private MafiaCountry country2;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        game.getRandom().setSeed(0);
        Map<City, List<Road>> network2 = new HashMap<>();

        country2 = new MafiaCountry("Country 2", network2);
        country2.setGame(game);
    }

    @Test
    public void bonus() throws Exception {
        for(int seed = 0; seed<1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            Set<Integer> values = new HashSet<>();
            for(int i = 0; i<50000; i++) {
                int bonus = country2.bonus(80);
                if(bonus < 0) {
                    robs++;
                    assertTrue(10 <= -bonus && -bonus <= 50);
                    loss -= bonus;
                    values.add(-bonus);
                }
            }
            assertTrue( 275000 < loss && loss < 325000);
            assertTrue(9000 < robs && robs < 11000);
            assertEquals(values.size(), 41);
        }
    }
}
