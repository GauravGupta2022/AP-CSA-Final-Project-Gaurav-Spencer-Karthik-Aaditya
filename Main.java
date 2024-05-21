import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    String roomNumber = null;
    int periodNumber =-1;
    int numRows = -1;
    int numCols = -1;
    int deskCount = -1;
    int studentCount = -1;
    Desk[][] desks = null;
    ArrayList<Student> students = new ArrayList<Student>();
    

    //List of available Classroom methods: fullRandom(), medicalFrontPreference(), medicalBackPreference(), gradePreference(), singleRandom(), personalPreference()
    System.out.println("Start of textfile reading");
    try {
      //filereader creation
			File teacherFile = new File("myFile.txt");
			teacherFile.createNewFile();
			FileReader fileReader = new FileReader(teacherFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			roomNumber = reader.readLine();
      periodNumber = getDigitsFromString(reader.readLine());
			numRows = getDigitsFromString(reader.readLine());
			numCols = getDigitsFromString(reader.readLine());
      desks = new Desk[numRows][numCols];
			deskCount = getDigitsFromString(reader.readLine());

      Desk[][] tempDesk = getValidDesks(numRows, numCols, reader);
      for (int r=0; r<numRows; r++){
        for (int c=0; c<numCols; c++){
          desks[r][c] = tempDesk[r][c];
        }
      }
      

      studentCount = getDigitsFromString(reader.readLine());
      setStudentAttributes(reader, students, studentCount);

      

			reader.close(); //END OF TEXT FILE READING
			
			
		} catch (NumberFormatException e) {
			System.out.println("There was error with INPUT!");
		} catch (NullPointerException e){
      System.out.println("An object was not initialized properly (NullPointerException)");
    } catch (ArrayIndexOutOfBoundsException e){
      System.out.println("Your inputs did not match the number of arguments (AIOOBE)");
    }
		catch (Exception e) {
			System.out.println("There was an unexpected error!");
		}
    finally { //Printing for testing - this block will ALWAYS BE RUN
			System.out.println("Room number: " + roomNumber);
			System.out.println("Number of rows: "+ numRows);
			System.out.println("Number of colums: " +numCols);
      System.out.println("Total desks: " +deskCount);
      System.out.println("Total students: "+studentCount);
    }

    

    //bob.initialize(classroom.getNumRows(), classroom.getNumCols(), )

    

    ///START OF PROGRAM
    Classroom classroom = new Classroom(deskCount, studentCount, desks, roomNumber, periodNumber, students);
    //CREATE GUI OBJECT HERE that takes in appropriate parameters from the classroom 
    System.out.println("Welcome to Seating Plus!");
    Scanner input = new Scanner(System.in);
    Student myStudent = new Student();
    System.out.println(myStudent);
    
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

    //panel.initialize(classroom.getNumRows(), classroom.getNumCols(), classroom.getID(), classroom.getName());
  }

  private static void setStudentAttributes(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception {
    try {
      for (int i=0; i<studentCount; i++){
        students.add(new Student(reader.readLine())); //names
      }
      for (int i=0; i<studentCount; i++){
        students.get(i).setId(reader.readLine()); //ids
      }
      for (int i=0; i<studentCount; i++){
        students.get(i).setYear(getDigitsFromString(reader.readLine())); //years
      }
      //heights
      //medicalFrontPreference
      //medicalbackPreference
      //grade in class
      //friendid
      //wantedRow
      //wantedCol
    } catch (Exception e) {
      throw e;
    }
  }

  private static int getDigitsFromString(String s){
    String temp = "";
    for (int i=0; i<s.length(); i++){
      char tempChar = s.charAt(i);
      if(Character.isDigit(tempChar)){
        temp += tempChar;
      }
      else {
        break;
      }
    }
    return Integer.parseInt(temp);
  }

  private static Desk[][] getValidDesks(int numRows, int numCols, BufferedReader reader) throws Exception {
    Desk[][] desks = new Desk[numRows][numCols];
    try {
      for (int r=0; r<numRows; r++){ //every row of desks
        String temp = reader.readLine().toLowerCase();
        if (temp.length()!=numCols){
          throw new ArrayIndexOutOfBoundsException();
        }
        for (int c=0; c<temp.length(); c++){
          if (temp.substring(c, c+1).equals("o")){
              desks[r][c] = new Desk();
          }
          else if (temp.substring(c, c+1).equals("x")){
            desks[r][c] = null;
          }
        }
    }
    } catch (Exception e) {
      throw e;
    }
    return desks;
  }
      

}
