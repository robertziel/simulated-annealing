package edu.pjatk.nai;

import edu.pjatk.nai.algorithm.Algorithm;
import edu.pjatk.nai.city.CityHolder;
import edu.pjatk.nai.algorithm.impl.SimulatedAnnealing;
import edu.pjatk.nai.tour.Tour;

import java.nio.file.Paths;

public class Runner {
    
    static {
        CityHolder.init(Paths.get("./data/japan.tsp"));
    }
    
    public static void main(String[] args) {
        final double initialTemperature = 1000;
        final double coolingRate = 0.0001;
        
        Algorithm algorithm = new SimulatedAnnealing(initialTemperature, coolingRate);
        Tour tour = algorithm.run();
    
        System.out.println("Final distance: " + tour.getDistance());
    }
}
