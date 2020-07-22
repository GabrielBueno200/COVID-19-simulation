/**
 * It contains the Hospital class
 */
package pkg_Hospital;

/**
 * This class is used to reference the world's hospitals. 
 * It contains an attribute to store the hospitals' colors, that are rendered on the map (color attr).
 * @author Bueno
 */
public class Hospital {
    protected int color;
    
    public Hospital(int color){
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
    
}
