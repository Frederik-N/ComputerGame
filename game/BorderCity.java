/**
 * A borderCity is placed within a country and has a specific value, that changes when the players arrive.
 * @author Jonas Madsen || Frederik Nielsen
 * @version 26.11.2017
 */
public class BorderCity extends City {

    /**
     * Creates a new BorderCity object
     *
     * @param name    Name of this bordercity
     * @param value   Value of this bordercity
     * @param country Which country the bordercity is placed within
     */
    public BorderCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     *  Makes the player pay a toll additional to the bonus he gets, if he arrives in another country.
     * @param p A player.
     * @return super.arrive() which returns a bonus or it subtracts a toll from bonus.
     */
    @Override
    public int arrive(Player p) {
        if (p.getCountryFrom() != getCountry()) {
            int toll = ((p.getMoney() * (getCountry().getGame().getSettings().getTollToBePaid()))/100);
            int bonus = super.arrive();
            changeValue(toll);
            return bonus - toll;
        }
        return super.arrive();
    }
}
