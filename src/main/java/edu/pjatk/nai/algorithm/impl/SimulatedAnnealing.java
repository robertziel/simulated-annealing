package edu.pjatk.nai.algorithm.impl;

import edu.pjatk.nai.algorithm.Algorithm;
import edu.pjatk.nai.tour.Tour;


public class SimulatedAnnealing implements Algorithm {
    
    /** Initial temperature for system. */
    private final double initTemp;
    
    /** The cooling rate for the system. */
    private final double coolingRate;
    
    
    public SimulatedAnnealing(double initTemp, double coolingRate) {
        this.initTemp = initTemp;
        this.coolingRate = coolingRate;
    }
    
    
    public Tour run() {
        int comparisons = 0;
        int acceptances = 0;
        
        // tours initialization
        Tour initialSolution = Tour.random();
        Tour currentSolution = new Tour(initialSolution);
        Tour bestSolution = new Tour(currentSolution);
    
        // setting initial temperature
        double temp = initTemp;
        
        while (temp > 1) {
            // new neighboring tour
            Tour newSolution = currentSolution.neighbour();
            
            // Get energy of solutions
            final double currentEnergy = currentSolution.getDistance();
            final double neighbourEnergy = newSolution.getDistance();
            
            // decide if we should accept neighbour
            comparisons++;
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) >= Math.random()) {
                acceptances++;
                final double acceptancePercentage = acceptances * 100 / comparisons;
                double delta = (neighbourEnergy - currentEnergy);
                String deltaInfo = delta > 0. ? ",\t worse" : "";
                System.out.println(String.format("initTemp: %s,\tdist: %s,\tdelta: %s, comparisons: %s, acceptances: %s, acceptancePercentage: %s%s", temp, neighbourEnergy, delta, comparisons, acceptances, acceptancePercentage, deltaInfo));
                currentSolution = new Tour(newSolution);
            }
            
            // updating best solution
            if (currentSolution.getDistance() < bestSolution.getDistance()) {
                bestSolution = new Tour(currentSolution);
            }
    
            // cooling the system
            temp *= 1 - coolingRate;
        }
        return bestSolution;
    }
    
    /**
     * Calculates the acceptance probability based on given energy of current and potential solution 
     * (includes current temperature).
     *
     * @param energy current solution energy
     * @param newEnergy potential solution energy
     * @param temperature current temperature
     * @return the acceptance probability.
     */
    private static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        return newEnergy < energy 
               ? 1.0 
               : Math.exp((energy - newEnergy) / temperature);
    }
}
