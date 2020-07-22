/**
 * It contains the World class
 */
package pkg_World;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import pkg_People.*;
import pkg_Hospital.Hospital;
import pkg_Virus.Virus;

/**
 *
 * This class is used to generate and update the world map. 
 * It's composed by the own world map, the people (healthy and sick) and hospitals.
 * @author Bueno
 */
public class World {
    ArrayList<Person> people = new ArrayList<>();
    ArrayList<Hospital> hosp = new ArrayList<>();
    
    /*Map Matrix - defines the World's map format:
        0 - street
        1 - borders
        2, 3 and 4 - hospitals
        5 - hospitals' cross
    */
    protected int map[][] = 
    {   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4,4,4,0,0,0,1},
        {1,0,0,0,2,2,2,5,5,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,5,5,4,4,4,0,0,0,1},
        {1,0,0,0,2,5,5,5,5,5,5,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,5,5,5,5,5,5,4,0,0,0,1},
        {1,0,0,0,2,2,2,5,5,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,5,5,4,4,4,0,0,0,1},
        {1,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4,4,4,0,0,0,1},
        {1,0,0,0,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,4,4,4,4,4,4,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,5,5,3,3,3,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,5,5,5,5,5,5,3,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,5,5,3,3,3,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    
    /**
     * World constructor - It generates and adds people and hospitals objects to different ArrayLists,
     *                     defining the world.
     */
    public World(){
        
        int nPeople = catchPeopleNumber();
        
        ThreadLocalRandom random = ThreadLocalRandom.current();

        ArrayList<Integer> colors = new ArrayList<>();
        
        for(int i = 0; i < 3; i++){
            int color = random.nextInt(43,47);
            while (colors.contains(color)){
                color = random.nextInt(43,47);
            }
            colors.add(color);
            hosp.add(new Hospital(color));
        }
        for(int j = 0; j < nPeople; j++){
            
            if (j < nPeople - 1)
                people.add(new HealthyPerson(random.nextInt(0,60), random.nextInt(0,30), 42)); 
            else{
                int x = random.nextInt(0,60), y = random.nextInt(0,30);
                while(map[y][x] > 1){
                    x = random.nextInt(0,60);
                    y = random.nextInt(0,30);
                }
                people.add(new SickPerson(x, y, 41, 0, new Virus()));
            }
            
        }
    }
    
    /**
     * This method asks to the user the number of people
     * that will be used in the simulation
     * @return number of people
     */
    public final int catchPeopleNumber(){
        String option = null;
        int nPeople;
        while (true){
            try{
                option = JOptionPane.showInputDialog(
                                              "  ==========================\n"
                                            + "     CORONAVIRUS SIMULATION\n"
                                            
                                            + "  ==========================\n"
                                            + "\nSELECT THE SIMULATION OPTION:\n"
                                            + "A - 10 healthy people and 1 infected\n"
                                            + "B - 100 healthy people and 1 infected\n\n");
                option = option.toUpperCase();
            
                if (option.equals("A") || option.equals("B")){
                    nPeople = (option.equals("B")) ? 101 : 11;
                    break;
                }else
                    JOptionPane.showMessageDialog(null, "Opção Inválida!");
            } catch (Exception e){
                System.exit(0);
            }      
        }
        
        return nPeople;
    }
    
    /**
     * This method draws the world according the colors and positions of map matrix.
     * If the matrix position at [i][j] and some person's position at [y][x] are the same, the
     * person's color will be printed, otherwise the map's standard color at the position [i][j] of the matrix.
     */
    public void drawWorld(){
        ArrayList<Point> pos = new ArrayList<>();
        boolean printedPerson;

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                
                printedPerson = false;
               
                for(int k = 0; k < people.size(); k++){
                    int x = people.get(k).getX(), y = people.get(k).getY();

                    if (i == y && j == x){
                        if (!pos.contains(new Point(y,x))){
                            System.out.printf("\033[%dm ", people.get(k).getColor());
                            pos.add(new Point(y, x));
                        }
                        printedPerson = true;
                    }
                      
                }
                
                if (!printedPerson){
                    switch (map[i][j]) {
                        case 0:
                            System.out.printf("\033[40m ");
                            break;
                        case 1:
                            System.out.printf("\033[47m ");
                            break;
                        case 2:
                            System.out.printf("\033[%dm ", hosp.get(0).getColor());
                            break;
                        case 3:
                            System.out.printf("\033[%dm ", hosp.get(1).getColor());
                            break;
                        case 4:
                            System.out.printf("\033[%dm ", hosp.get(2).getColor());
                            break;
                        default:
                            System.out.printf("\033[40m ");
                            break;
                    }
                }

            }
            System.out.println("");
        }
        

    }
    
    /**
     * This method checks if the HelthyPerson object given collided with any SickPerson object, if so,
     * a new object of SickPerson will be inserted at the specified person's position
     * and the HealthyPerson object provided will be removed (both operations happen on People ArrayList)
     * 
     * @param person Specific HealthyPerson object given by the main loop (on the Main class) 
     * @param time The runtime given by the main loop (in seconds)
     */
    public void verifyCollision(HealthyPerson person, float time){
        
        ArrayList<Point> posInfected = new ArrayList<>();
        
        people.forEach((p) -> {
            if (p.getClass() == SickPerson.class)
                posInfected.add( new Point( p.getX(), p.getY() ) );
        });
        
        if (posInfected.contains(new Point(person.getX(), person.getY()))){
            people.add(new SickPerson(person.getX(), person.getY(), 41, time, new Virus()));
            people.remove(person);
        }
        
    }
    
    /**
     * This method checks if the position of provided SickPerson object is the same of
     * some hospital on the map matrix, if so,
     * a new object of HealthyPerson will be inserted at the specified person's position
     * and the SickPerson object provided will be removed (both operations happen on People ArrayList)
     * 
     * @param p Specific SickPerson object given by the main loop (on the Main class) 
     */
    public void cureSickPerson(SickPerson p){
        if (map[p.getY()][p.getX()] > 1){
            people.add(new HealthyPerson(p.getX(), p.getY(), 42));
            people.remove(p);
        }
        
    }
    
    
    /**
     * This method checks if the lifeTime of provided SickPerson object has reached 30 seconds
     * @param time - The runtime given by the main loop (in seconds)
     * 
     * @param p Specific SickPerson object given by the main loop (on the Main class) 
     * @return 1, if the person dies else 0
     */
    public int killSickPerson(float time, SickPerson p){
        int n = 0;
        if (time == p.getLifeTime() + 30){
            people.remove(p);
            n++;
        }
        
        return n;
    }
    
    /**
     * This method counts the numbers of HealthyPerson and SickPerson objects inside the People ArrayList
     * and returns an array containing them.
     * @return array (length = 2) containing the numbers of HealthyPerson and SickPerson objects 
     */
    public int[] getPeopleNumbers(){
        int[] n = {0,0};
        for(int i = 0; i < people.size(); i++){
            Person p = people.get(i);
            if (p.getClass() == HealthyPerson.class)
                n[0]++;
            else
                n[1]++;
        }
        return n;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public ArrayList<Hospital> getHosp() {
        return hosp;
    }

    public int[][] getMap() {
        return map;
    }
    
}
