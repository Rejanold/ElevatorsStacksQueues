ReadMe for Elevator Stacks and Queues Homework.
CS 245
Dr. Amthauer
Robert Hable

1) As there is no specifics in the homework assignment I will be including ever java program in order to 
   run the code this includes:
	ElevatorAction.java (Driver)
	Floor.java	    
	List.java
	Node.java
	Person.java
	Queue.java
	QueueList.java
	SLList.java
	Stack.java
	StackList.java
	Readme.txt
   I did not include a commands file or names file as I am making the assumption Dr. Amthauer will have
   her own test files.
   In order to get this code to compile all the above files must be located in the same directory with
   the exception of the ReadMe file. The test files e.g. Commands and Names must also be present in the
   directory.

2) Read above and place nessasary files into the same directory. Once all files are together you will 
   need to compile the code from command line using javac *.java. Then to run the program pass the following
   command: java ElevatorAction your_names_file.txt your_commands_file.txt
   There are two command line arguments above after ElevatorAction the first is the names file the second
   is the commands file you wish to use. 

3) We started very fooloishly and not taking what we learned from our BioInfo homeowrk and tried making
   making individual lists, this lasted roughly 30 minuets until we realised we were foolish and working 
   too hard. So we decided to make our own Class called floor very similar to sequence from bio info 
   with not nearly as complex methods. We made a Person class to represent each person as its own object
   and we placed these Person objects into an array full of floor objects utilizing SLLists. After we got
   got this done the rest of the code was quick and pretty strait forward. Utilizing the stack for the 
   Elevator because the description screamed Stack and the line to get on Screamed queue so we used a queue.
   Then we read commands from the command file and implement the actions as neccesary.

4) Issues were mostly at the beginning and deciding how we wanted to implement floors and person objects.
   That was the biggest hurdle. Now that the program is complete I think using stack array
   would have been the better approach, not because it was easier but because we had a set size for the 
   stack and it is more effecient to use an array that never needs to change. Where as the queue was more
   varried so the QueueList was obviously the better option. We did not use the Stack array because we 
   like Lists after the last few weeks but during the design phase we should have considered an array.