import java.util.Scanner;
import java.lang.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.http.HttpRequest;

public class Main {
  public static void main(String[] args) {
    //List of available Classroom methods: fullRandom(), medicalFrontPreference(), medicalBackPreference(), gradePreference(), singleRandom(), personalPreference()
    System.out.println("Start of program");
    //fileReader creation
    try {
			File teacherFile = new File("myFile.txt");
			teacherFile.createNewFile();
			
			FileReader fileReader = new FileReader(teacherFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			String roomNumber = reader.readLine();
			String temp = reader.readLine();
			if (temp.indexOf(" ")!=-1){
				int numRows = Integer.parseInt(temp.substring(0, temp.indexOf(" ")));
			}
			int numCols = Integer.parseInt(reader.readLine());
			int totalDesks = Integer.parseInt(reader.readLine());

			//Prining
			System.out.println(roomNumber);
			System.out.println(numRows);
			System.out.println(numCols);

			
			reader.close();
			
			
		} catch (NumberFormatException e) {
			System.out.println("There was error with INPUT!");
		}
		catch (Exception e) {
			System.out.println("There was an error!");
		}

    //bob.initialize(classroom.getNumRows(), classroom.getNumCols(), )

    

    ///START OF PROGRAM
    System.out.println("Welcome to Seating Plus!");
    Scanner input = new Scanner(System.in);
    Student myStudent = new Student();
    System.out.println(myStudent);
    Classroom classroom = new Classroom(); //replace with proper constructor
    System.out.println("Hello, would you like to get a completely randomized desk setup [1], to let your students choose their own desks [2], or to choose the desk arrangement yourself [3]");
    String response = input.next();
    //NOTE: Must prompt teacher for int deskCount, int studentCount, Desk[][] desks, int classNumber, int periodNumber, ArrayList<Student> students in order to fill the Classroom constructor...
    if (response.contains("1")){//completely randomized
      classroom.fullRandom();
    }
    else if (response.contains("2")){//students choose own desks
      //code
    }
    else{//teacher chooses desk arrangement
      //code
    }
    System.out.println("Thank you for using Seating Plus! We hope you enjoyed your experience!");

    panel.initialize(classroom.getNumRows(), classroom.getNumCols(), classroom.getID(), classroom.getName());
  }

}
