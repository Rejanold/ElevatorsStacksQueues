import java.util.*;
import java.io.*;
/**
 * Person object to create individual instates of Persons
 * CS245
 * Dr. Amthauer
 * Authors: Robert Hable, Blake Furlano and Mason Waters
 * Date: 04 Nov 2019
 */
public class Person {
    private String name;
    private int floor;

    /**
     * Person constructor
     * @param name what the persons name is
     * @param floor what floor they are currently on
     */
    public Person (String name, int floor) {
        this.name = name;
        this.floor = floor;
    }

    /**
     * Returns the persons name.
     * @return persons name
     */
    public String getName(){
        return name;
    }

    /**
     * returns the current floor of said person.
     * @return the floor their dwelling on.
     */
    public int getFloor(){
        return floor;
    }

    /**
     * Changes the floor that the person is on.
     */
    public void setFloor(){
        this.floor = floor;
    }

    /**
     * To String method that we always need
     * @return the String
     */
    public String toString(){
        return name + " ";
    }
}
