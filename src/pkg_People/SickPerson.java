
package pkg_People;

import pkg_Virus.Virus;

/**
 * This is a child of Person class and it's a model for sick person. 
 * It contains special/extras attributes, described on their respective lines.
 * (it aggregates the Virus class)
 * 
 * @author Bueno
 */
public class SickPerson extends Person {
    
    protected float lifeTime; //used to reference the initial time of infection
    protected Virus virus; //used to reference the virus inside the person
    
    /**
     * Sick Person constructor - defines the object attributes
     * @param x X position on map (on matrix columns)
     * @param y Y position on map (on matrix lines)
     * @param color Its color
     * @param lifetime The time which the person was infected
     * @param virus Object of virus to simulate the coronavirus inside the person
     */
    public SickPerson(int x, int y, int color, float lifetime, Virus virus) {
        super(x, y, color);
        this.lifeTime = lifetime;
        this.virus = virus;
    }
    

    public float getLifeTime() {
        return lifeTime;
    }

    
    
    
}
