import java.util.*;
import java.io.*;
/**
 * Floor object utilizing SLList to add Person Objects to Nodes
 * CS245
 * Dr. Amthauer
 * Authors: Robert Hable, Blake Furlano and Mason Waters
 * Date: 04 Nov 2019
 */
public class Floor {
    private SLList<Person> occupants;
    private int floorNum;
    private QueueList<Person> queLine;

    /**
     * Constructor
     * @param floorNum what number is the floor
     */
    public Floor(int floorNum){
        this.floorNum = floorNum;
        occupants = new SLList<Person>();
        queLine = new QueueList<Person>();
    }

    /**
     * Add a Person Object
     * @param y The Person Object
     */
    public void addPerson(Person y){
        occupants.add(y);
    }

    /**
     * Test method
     */
    public void printFloor(){
        System.out.println(occupants.toStringNoArrow());
    }

    /**
     * SLLlist of type Persons checking whos in the list
     * @return the persons
     */
    public SLList<Person> getOccupants(){
        return occupants;
    }

    /**
     * Using our QueueList to have Person objects wait to get on the elevator
     * @param p Person Object
     * @param i Remove the Person from the SLList
     */
    public void addToQue(Person p, int i){
        queLine.enqueue(p);
        occupants.remove(i);

    }

    /**
     * Whos the Person
     * @param i what person at the given position
     * @return the person
     */
    public Person getPerson(int i){
        return occupants.getValue(i);
    }

    /**
     * Didnt use this method
     * @return nothing because its not used
     */
    public String getNameOfFirstPersonInQueue(){
        return queLine.front().getName();

    }

    /**
     * How many people are in queue
     * @return return how many people are in line
     */
    public QueueList<Person> getWaitLine(){
        return queLine;
    }
}
