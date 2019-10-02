package edu.pjatk.nai.city;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class CityHolder {
    
    // Holds our cities
    private static List<City> cities = new ArrayList<>();
    
    
    // Adds a destination city
    public static void add(City city) {
        cities.add(city);
    }
    
    // Get a city
    public static City get(int index) {
        return cities.get(index);
    }
    
    // Get the number of destination cities
    public static int count() {
        return cities.size();
    }
    
    public static void init(Path path) throws RuntimeException {
        try {
            boolean readCity = false;
            for (String line : Files.readAllLines(path)) {
                if ("EOF".equals(line.trim())) {
                    break;
                }
                if (readCity) {
                    String[] split = line.split(" ");
                    final double x = Double.parseDouble(split[1]);
                    final double y = Double.parseDouble(split[2]);
                    add(new City(x, y));
                } else if ("NODE_COORD_SECTION".equals(line.trim())) {
                    readCity = true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
}
