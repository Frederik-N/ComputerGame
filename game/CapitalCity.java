/**
 * A capitalCity is placed within a country and has a specific value, that changes when the players arrive.
 * @author Jonas Madsen || Frederik Nielsen
 * @version 26.11.2017
 */
public class CapitalCity extends BorderCity {
    /**
     * Creates a new CapitalCity object
     *
     * @param name    Name of this city
     * @param value   Value of this city
     * @param country Which country the city is placed within
     */
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     * Makes the player pay consumption additional to the bonus and toll he gets.
     * @param p A player.
     * @return the bonus where both consumption and toll is subtracted
     */
    @Override
    public int arrive(Player p) {
        int arrive = super.arrive(p);
        int consumption = p.getCountry().getGame().getRandom().nextInt(p.getMoney()+arrive+1);
        changeValue(consumption);
        return arrive-consumption;
    }
}
