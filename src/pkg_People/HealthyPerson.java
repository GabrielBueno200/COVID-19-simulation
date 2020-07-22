package pkg_People;


/**
 * This is a child of Person class and it's a model for healthy person. 
 * It doesn't contain special/extras attributes or methods.
 * 
 * @author Bueno
 */
public class HealthyPerson extends Person{
    
    /**
     * Healthy Person constructor - defines the object attributes
     * @param x X position on map (on matrix columns)
     * @param y Y position on map (on matrix lines)
     * @param color Its color
     */
    public HealthyPerson(int x, int y, int color) {
        super(x, y, color);
    }
    
}
