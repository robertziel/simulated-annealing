package edu.pjatk.nai.tour;

import edu.pjatk.nai.city.CityHolder;
import edu.pjatk.nai.city.City;

import java.util.ArrayList;
import java.util.Collections;

public class Tour extends ArrayList<City> {
    
    private double distance = 0;
    
    
    public Tour(){
        super(CityHolder.count());
        for (int i = 0; i < CityHolder.count(); i++) {
            add(null);
        }
    }
    
    public Tour(Tour tour) {
        super(tour.size());
        addAll(tour);
        this.distance = tour.distance;
    }
    
    
    // Gets a city from the cities
    public City getCity(int tourPosition) {
        return get(tourPosition);
    }
    
    // Sets a city in a certain position within a cities
    public void setCity(int tourPosition, City city) {
        set(tourPosition, city);
        distance = 0; // If the tours been altered we need to reset the fitness and distance
    }
    
    // Gets the total distance of the cities
    public double getDistance() {
        if (distance == 0) {
            double tourDistance = 0;
            for (int cityIndex = 0; cityIndex < size(); cityIndex++) {
                City fromCity = getCity(cityIndex);
                City destinationCity = (cityIndex + 1 < size())
                                       ? getCity(cityIndex + 1)
                                       : getCity(0);
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }
    
    public Tour neighbour() {
        Tour neighbour = new Tour(this);
        
        // Get a random positions in the tour
        int tourPos1 = (int) (neighbour.size() * Math.random());
        int tourPos2 = (int) (neighbour.size() * Math.random());
    
        // Get the cities at selected positions in the tour
        City citySwap1 = neighbour.getCity(tourPos1);
        City citySwap2 = neighbour.getCity(tourPos2);
    
        // Swap them
        neighbour.setCity(tourPos2, citySwap1);
        neighbour.setCity(tourPos1, citySwap2);
    
        return neighbour;
    }
    
    public static Tour random(){
        Tour tour = new Tour();
        for (int cityIndex = 0; cityIndex < CityHolder.count(); cityIndex++) {
            tour.setCity(cityIndex, CityHolder.get(cityIndex));
        }
        Collections.shuffle(tour); // Randomly reorder the cities
        return tour;
    }
}
