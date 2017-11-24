public class CapitalCity extends BorderCity {
    /**
     * Creates a new City object
     *
     * @param name    Name of this city
     * @param value   Value of this city
     * @param country Which country the city is placed within
     */
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    @Override
    public int arrive(Player p) {
        int arrive = super.arrive(p);
        int consumption = p.getCountry().getGame().getRandom().nextInt(p.getMoney()+1)+arrive;
        changeValue(consumption);
        return arrive-consumption;
    }
}
