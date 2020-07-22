/**
 * It contains the classes associated with people (Person, SickPerson and HealthyPerson)
 */
package pkg_People;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The parent class of HealthyPerson and SickPerson classes. 
 * It models people in general.
 * @author Bueno
 */
public abstract class Person {
    protected int x;
    protected int y;
    protected int color;
    
    /**
     * Person constructor - catches the parameters given by child classes (to define the objects)
     * @param x - X position (on matrix columns)
     * @param y - Y position (on matrix lines)
     * @param color - its colors
     */
    public Person(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    /**    
     * This method is used to update the person position on the world map,
     * generating a random number (0 or 1), which is stored on "diagonal" variable.
     * 
     * If the variable "diagonal" be equals 1, the person will move in "diagonal" movements:
     * The variable "signal" will be generated (0 or 1)
     * If "signal" be equals 0 the person will do regressive movements 
     * else progressive movements
     * 
     * 
     * If the variable "diagonal" be equals 0, the person will move in 4 possible movements
     * (up, down, left, right), these movements will depend on the variable "movement" (0 until 5).
    */ 
     
    public void move(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int diagonal = random.nextInt(0,2);
        
        if (diagonal == 1){     
            int signal = random.nextInt(0,2);

            if(signal == 0)
                x = (x + 1 > 59) ? 0 : x + 1;
            else 
                x = (x-1 < 0) ? 59 : x - 1;

            signal = random.nextInt(0,2);

            if(signal == 0) 
                y = (y + 1 > 29) ? 0 : y + 1;
            else 
                y = (y-1 < 0) ? 29 : y - 1;
        } else{
 
            int movement = random.nextInt(0,4);
            
            if(movement == 0) 
                x = (x + 1 > 59) ? 0 : x + 1;
            else if (movement == 1) 
                x = (x-1 < 0) ? 59 : x - 1;
            else if (movement == 2) 
                y = (y + 1 > 29) ? 0 : y + 1;
            else if (movement == 3)
                y = (y-1 < 0) ? 29 : y - 1;
        }
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getColor() {
        return color;
    }



}
