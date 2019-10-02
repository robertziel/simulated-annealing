package edu.pjatk.nai.city;

/**
 * Represents the city object.
 */
public class City {
    
    /** X coordinate. */
    private final double x;
    
    /** Y coordinate. */
    private final double y;
    
    
    City(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Calculates the cartesian distance to given city.
     *
     * @param city the city to calculate distance.
     * @return the distance.
     */
    public double distanceTo(City city) {
        final double xDistance = Math.abs(x - city.x);
        final double yDistance = Math.abs(y - city.y);
        return Math.sqrt((Math.pow(xDistance, 2)) + (Math.pow(yDistance, 2)));
    }
}
