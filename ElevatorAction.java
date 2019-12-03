import java.util.*;
import java.io.*;

/**
 * Stacks and Queues Homework implementing a Queue as a wait line to get on an elevator
 * and a Stack for the People that are on the elevator currently.
 * Inspects the elevator and queue and reports pertinent information about
 * the status of whos first in line whos first to get off the elevator.
 * CS245
 * Dr. Amthauer
 * Authors: Robert Hable, Blake Furlano and Mason Waters
 * Date: 04 Nov 2019
 */
public class ElevatorAction {
    public static final int ELEVATOR_SIZE = 7; //Class constant for the ammount of people allowed on the elevator.
    public static final int FLOOR_SIZE = 4; // Class constant for the number of floors in our building.
    public static void main(String[] args) throws FileNotFoundException {
        File person = new File(""); // File to add people objects to floor lists
        File command = new File(""); // Commands file passed to the program
        Floor[] building = new Floor[4]; //Array storing our Floor lists making each list its own floor.
        StackList<Person> stackList = new StackList<Person>(); // StackList for the elevator of type Person
        if(args.length == 2){ // accepting only 2 command line arguments
            person = new File(fileValidation(args[0])); //Person file passed first
            command = new File(fileValidation(args[1])); // Command file passed second
            Scanner nameInput = new Scanner(person); //Scanning our person file
            creatFloors(building); //Creating new floors method
            while(nameInput.hasNextLine()){ //reading names and do we have more names to read
                String line = nameInput.nextLine(); //Storing names into a string
                if(!line.trim().equals("")) { // cleaning the file
                    Scanner lineScan = new Scanner(line); //reading the names
                    fillFloors(lineScan, building); //Method to build lists and add to the array
                }
            }
            Scanner commandInput = new Scanner(command); //Reading the command file
            while(commandInput.hasNextLine()) { // does it have input
                String heatherCommand = commandInput.nextLine(); //reading the command and do we have more to do
                if(!heatherCommand.trim().equals("")) { //cleaning the file
                    Scanner commandScan = new Scanner(heatherCommand); // reading the command
                    whatToDo(commandScan, building, stackList); //method deciding what to do with the command passed.
                }

            }
        }
        else{ //invalid file input
            System.out.println("File passed was not found in the directory!");

        }
    }

    /**
     * A method to ensure the file passed in command arguments actually exists.
     * This method may be able to be replaced with Try/Catch statements I will try that
     * next time.
     * @param fileName the name of the file passed from command line
     * @return if valid filename it returns the filename
     * @throws FileNotFoundException if the file doesnt exist it makes an exception.
     */
    public static String fileValidation(String fileName) throws FileNotFoundException {
        File file = new File(fileName); //File
        if (!file.exists()) { //check if the file exist in the directory
            System.out.println("That is not present"); //it doesnt exist print a message to the user
            System.exit(0); //Die nicely
        }
        return fileName; //File exists send it.
    }

    /**
     * Method to accept the Strings from our files and build Person objects then insert them into
     * lists and insert them into our floors array.
     * @param lineScan Scanner to read the file
     * @param building Array of lists for our floor
     */
    public static void fillFloors(Scanner lineScan, Floor[] building ){
        String name = lineScan.next(); //Persons name
        int floor = lineScan.nextInt(); // What floor they are moving into.
        //All these check the floor int and inserts the Person Object into the correct list.
        if(floor == 0){
            building[0].addPerson(new Person(name, floor));
        }else if(floor == 1){
            building[1].addPerson(new Person(name, floor));
        }else if(floor == 2){
            building[2].addPerson(new Person(name, floor));
        }else if(floor == 3){
            building[3].addPerson(new Person(name, floor));
        }

    }

    /**
     * A simple method to insert Floor lists into our building array
     * @param building returns a list filled array
     */
    public static void creatFloors(Floor[] building){
        for(int i = 0; i < FLOOR_SIZE; i++){
            building[i] = new Floor(i); // new list at each array index
        }
    }

    /**
     * Test method to ensure Person types are inserted into the proper lists
     * @param building our array of floors
     */
    public static void printAllFloors(Floor[] building){
        building[0].printFloor();
        building[1].printFloor();
        building[2].printFloor();
        building[3].printFloor();

    }

    /**
     * A method to read the commands from the command file and call athe appropriate method.
     * @param lineScan Scanner to read computer stuffs
     * @param building array of floors
     * @param elevator Stacklist for inside the elevator
     */
    public static void whatToDo(Scanner lineScan, Floor[] building, StackList<Person> elevator){
        String command = lineScan.next(); //what command is passed from the file
        if(command.equalsIgnoreCase("WAIT")){
            int floorNum = lineScan.nextInt(); //what floor
            try {
                String personName = lineScan.next(); //What person
                personWait(floorNum, personName, building); //send information to the Wait Method
            }
            catch(NoSuchElementException e){
                System.out.println("Floor " + floorNum + " does not exist and the elevator isn't like Willy Wonkas!");
            }
        }
        else if(command.equalsIgnoreCase("PICK_UP")) {
            int floorNum = lineScan.nextInt(); //what floor
            try {
                pickUp(floorNum, building, elevator); //send information to pick up method
            }
            catch(NoSuchElementException e){
                System.out.println("Floor " + floorNum + " does not exist and the elevator isn't like Willy Wonkas!");
            }
        }
        else if(command.equalsIgnoreCase("DROP_OFF")){
            int floorNum = lineScan.nextInt(); // what floor
            try {
                int escapees = lineScan.nextInt(); //whos getting out of the stack
                dropOff(floorNum, escapees, building, elevator); //sends information to the drop off method
            }catch(NoSuchElementException e) {
                System.out.println("Floor " + floorNum + " does not exist and the elevator isn't like Willy Wonkas!");
            }
        }
        else if(command.equalsIgnoreCase("INSPECTION")){
            inspection(building, elevator);//sneds information to inspection method.
        }
    }

    /**
     * Method to add people to our queue to get onto the elevator
     * @param floorNum the floor
     * @param personName who are we adding
     * @param building the building array of floors
     */
    public static void personWait(int floorNum, String personName, Floor[] building){ //Error checking
        Person n; //whos that person
        if(floorNum < 0 || floorNum > 3){ //error checking figured out we needed try catch in what to do now i dont want to reformat this so its extra code
            System.out.println("Floor " + floorNum + " does not exist and the elevator isn't like Willy Wonkas!");
        }
        else {
            int numOfPeeps = building[floorNum].getOccupants().length();//how many people on the floor
            int notThere = 0; //the person is not on the floor heck
            for (int i = 0; i < numOfPeeps; i++) {
                if (personName.equalsIgnoreCase(building[floorNum].getOccupants().getValue(i).getName())) {//checking for the person
                    n = building[floorNum].getPerson(i);//checking the floor and the person
                    building[floorNum].addToQue(n, i); //add the person to the elevator queue
                    i = numOfPeeps; //person now in queue
                }
                else{
                    notThere += 1; //incrementing to see if we found the person
                }
            }
            if(notThere == numOfPeeps){ //checked all the persons and didnt find who was passed
                System.out.println(personName + " is not on floor " + floorNum);
            }
        }
        //System.out.println(building[floorNum].getNameOfFirstPersonInQueue());
    }

    /**
     * Method to take people from our Queue and put them into the elevator(stacklist)
     * @param floorNum what floor
     * @param building the floor array
     * @param elevator the Stacklist
     */
    public static void pickUp(int floorNum, Floor[] building, StackList<Person> elevator){
        if(floorNum < 0 || floorNum > 3){ //Error Checking
            System.out.println("Floor " + floorNum + " does not exist and the elevator isn't like Willy Wonkas!");
        }
        else{
            int elevatorSize = elevator.size(); //Size variable for the loop
            if(elevator.size() == ELEVATOR_SIZE){ //Checking if the elevator is full
                System.out.println("The elevator is full!");
            }
            while (elevatorSize < ELEVATOR_SIZE){ //Elevator not full time to work.
                if(!building[floorNum].getWaitLine().isEmpty()){//Are there persons in the queue
                    elevator.push(building[floorNum].getWaitLine().dequeue());//move person from queue to stack
                    elevatorSize ++;//increment persons on elevator
                }
                else{
                    elevatorSize = 8; //escape the eternal palace of infinity.............................oo
                }
            }
        }
    }

    /**
     * Popping method to take Persons from StackLists and put them back onto the floor
     * @param floorNum the floor
     * @param escapees whos getting off the elevator
     * @param building the floor array
     * @param elevator the Elevator stacklist.
     */
    public static void dropOff(int floorNum, int escapees, Floor[] building, StackList<Person> elevator){
        if(floorNum < 0 || floorNum > 3){//error checking
            System.out.println("Floor " + floorNum + " does not exist and the elevator isn't like Willy Wonkas!");
        }
        else{
            if(escapees >= elevator.size()){ //did the command pass a number larger than the size of the elevator
                int j = elevator.size();
                for(int i = 0; i < j; i++){
                    building[floorNum].getOccupants().add(elevator.pop()); //taking persons off the elevator
                }
            }
            else{
                if(escapees >= 0){
                    for(int i = 0; i < escapees; i++){
                        building[floorNum].getOccupants().add(elevator.pop());//taking persons off the elevator
                    }
                }
            }
            pickUp(floorNum, building, elevator);//we've finished emptying check if we need to pick anyone up
        }
    }

    /**
     * Method to report the status of the elevator
     * @param building floor array
     * @param elevator stacklist of the elevator
     */
    public static void inspection(Floor[] building, StackList<Person> elevator){
        System.out.println("Elevator Status: ");
        if(elevator.isEmpty()){//If the stacklist empty
            System.out.println("The elevator is empty. There are 7 open spots.");
            System.out.println("No one is on the elevator.");
        }
        else{//There are Persons on the elevator
            System.out.println("The elevator is not empty. There are " + (ELEVATOR_SIZE - elevator.size()
            + " open spots."));
            //Peek at the top of our stack and see whos getting off the elevator next
            System.out.println(elevator.peek().getName() + " will be the next person to leave the elevator.");
        }
        for(int i = 0; i< 4; i++){//checking each floor to see if people are in line
            if( building[i].getWaitLine().isEmpty()){ //no one is in line
                System.out.println("There are no people that want to get on the elevator from floor "
                + i + ".");
            }
            else{//checking the front of our queue to see whos getting on the elevator next
                System.out.println(building[i].getWaitLine().front().getName()
                + " will be the next person to get on the elevator from floor " + i + ".");
            }
        }
    }
}
