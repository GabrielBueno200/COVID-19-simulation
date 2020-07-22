/**
 * It contains the main class
 */
package pkg_Main;

import pkg_People.*;
import pkg_World.World;

/**
 * Main class of the program, it creates an object of world and simulates 
 * the dissemination speed of the COVID-19 around the world 
 * (with/without the quarentine practice ~~ 10 / 100 people simulation) 
 * 
 * @author Bueno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        World world = new World();
        
        int nDead = 0;
        float secondTime = 0;
        float sleepTime = 0;
        while (true){
            try {
                
                Thread.sleep(250);
                if (sleepTime % 4 == 0) secondTime += 1;
                System.out.println("  _,   _,   ,_     _,  ,  ,  _    ,   ,  ___,  ,_    ,  ,   _, \n" +
                                        " /    / \\,  |_)   / \\, |\\ | '|\\   \\  /  ' |    |_)   |  |  (_, \n" +
                                        "'\\_  '\\_/  '| \\  '\\_/  |'\\|  |-\\   \\/`   _|_, '| \\  '\\__|   _) \n" +
                                        "   `  '     '  `  '    '  `  '  `  '    '      '  `     `  '  ");
                System.out.println("Simulation Time: " + secondTime);
                System.out.println("\033[1;32m\u001B[40m:-)HEALTHY: " + world.getPeopleNumbers()[0]);
                System.out.println("\033[1;31m\u001B[40m:-(SICK: " + world.getPeopleNumbers()[1]);
                System.out.println("");
                System.out.println("ALIVE: " + (world.getPeopleNumbers()[1] + world.getPeopleNumbers()[0]));
                System.out.println("\u001B[36m\u001B[40m☠☠DEAD: " + nDead);
                System.out.println("");
                
                world.drawWorld();
                for(int i = 0; i < world.getPeople().size(); i++){
                    Person person = world.getPeople().get(i);
                    
                    if(person.getClass() == HealthyPerson.class){
                        world.verifyCollision((HealthyPerson)person, secondTime);
                    } else{
                        world.cureSickPerson((SickPerson)person);
                        nDead += world.killSickPerson(secondTime,(SickPerson)person);
                    }
                    
                    person.move();
                }
                sleepTime += 1;
            } catch (InterruptedException e) {
                System.out.println("Error: " + e);
            }
            
        }
    }
    
}
