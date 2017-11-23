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
     *
     * @param p
     * @return
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
