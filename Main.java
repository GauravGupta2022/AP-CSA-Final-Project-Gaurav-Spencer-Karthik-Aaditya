import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;



 public class Main {
 public static void main(String[] args) {
   JFrame frame = new JFrame("Classroom Layout");
   //List of available gridClassroom methods: fullRandom() [all students], eliteSeating() [GPA 1st choice], medicalFrontPreference()
   //gridClassroom methods continued: medicalBackPreference(), singleRandom() [1 student], medicalWithRandom() [medical front/back, then fullRandom()]
   //gridClassroom methods continued: gradePreference() [similar GPAs together], personalPreference() [sit with chosen friends] 
   //List of available GroupClassrom methods (+ those in GridClassroom): groupChoosing()

  //  SwingUtilities.invokeLater(ClassroomGUI::new);
  //  JFrame frame = new JFrame("Classroom Layout");
   //List of available Classroom methods: fullRandom(), medicalFrontPreference(), medicalBackPreference(), gradePreference(), singleRandom(), personalPreference()
    
   boolean usingGridSeating = true; //false = group seating
   final String textFileName = "GridSeating.txt";

   //variables to be filled in from Classroom
    String roomNumber = null;
    int periodNumber =-1;
    int numRows = -1;
    int numCols = -1;
    int deskCount = -1;
    int studentCount = -1;
    Desk[][] desks = null;
    ArrayList<Student> students = new ArrayList<Student>();
    int numInGroup = -1; //group seating

    System.out.println("Start of textfile reading");
    try {
      //filereader creation
			File teacherFile = new File(textFileName);
			teacherFile.createNewFile();
			FileReader fileReader = new FileReader(teacherFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
      if (reader.readLine().contains("grid")){
        usingGridSeating = true;
      }
      else {
        usingGridSeating = false;
      } 
			roomNumber = reader.readLine();
      periodNumber = getIntFromString(reader.readLine());
			numRows = getIntFromString(reader.readLine());
			numCols = getIntFromString(reader.readLine());
      desks = new Desk[numRows][numCols];
			deskCount = getIntFromString(reader.readLine());
      Desk[][] tempDesk = getValidDesks(numRows, numCols, reader);
      for (int r=0; r<numRows; r++){
        for (int c=0; c<numCols; c++){
          desks[r][c] = tempDesk[r][c];
        }
      }
      studentCount = getIntFromString(reader.readLine());
      if (!usingGridSeating){ //using group seating
        numInGroup = getIntFromString(reader.readLine());
      }
      
      throwIfNotEmptyLine(reader, "Error: Student names");
      if (usingGridSeating){
        setStudentAttributesGrid(reader, students, studentCount, numRows, numCols);
      }
      else { //using group seating
        setStudentAttributesGroup(reader, students, studentCount, numRows, numCols, numInGroup);
      }
      
			reader.close(); //END OF TEXT FILE READING
			
			
		} catch (NumberFormatException e) {
			System.out.println("There was error with INPUT!");
		} catch (NullPointerException e){
      // System.out.println("An object was not initialized properly (NullPointerException)");
    } catch (ArrayIndexOutOfBoundsException e){
      System.out.println("Your inputs did not match the number of arguments (AIOOBE)");
    }
		catch (Exception e) {
			System.out.println("There was an unexpected error!");
		}


    
    ///START OF PROGRAM
    Classroom classroom;
    if (usingGridSeating){
      classroom = new Classroom(deskCount, studentCount, desks, roomNumber, periodNumber, students);
    }
    else {
      classroom = new GroupClassroom(deskCount, studentCount, desks, roomNumber, periodNumber, students, numInGroup);
    }

    System.out.println("Room number: " + classroom.getRoomNumber());
		System.out.println("Number of rows: "+ classroom.getNumRows());
		System.out.println("Number of colums: " +classroom.getNumCols());
    System.out.println("Total desks: " +classroom.getDeskCount());
    System.out.println("Total students: "+classroom.getStudentCount());
    for (Student s : classroom.getStudentList()){
        System.out.println(s);
    }

 
    System.out.println("Welcome to Seating Plus!");
    Scanner input = new Scanner(System.in);
    Student myStudent = new Student();
    System.out.println(myStudent);
    System.out.println("Hello, would you like to get a completely randomized desk setup [1], let your students choose their own desks [2], or do you want to seat students by GPA [3]");
    String response = input.nextLine();
    //NOTE: Must prompt teacher for int deskCount, int studentCount, Desk[][] desks, int classNumber, int periodNumber, ArrayList<Student> students in order to fill the Classroom constructor...
    if (response.contains("1")){//completely randomized
      classroom.medicalFrontPreference();
      classroom.medicalBackPreference();
      classroom.fullRandom();
    }
      // if (response.contains("1")){
      //   classroom.fullRandom();
      // }
   else if (response.contains("2")){//students choose own desks
    classroom.medicalFrontPreference();
    classroom.medicalBackPreference();
    if (usingGridSeating){
      while (classroom.getStudentList().size()>0){
        classroom.personalPreference(classroom.singleRandom());
      }
     }
     else{
      //code with group
      String groupResponse = input.next();
      System.out.println("Press [1] to allow students to choose only one friend, press [2] to allow students to choose all friends");;
      if (groupResponse.equals("1")){
        while (classroom.getStudentList().size()>0){
          
          classroom.personalPreference(classroom.singleRandom());
        }
      }
      else{
      //code with CHOOSE ALL group  
      classroom.groupChoosing();
      }
     }
     }
     else if (response.contains("3")){//seat by similar GPA
      String holder;
      while (classroom.getStudentList().size()>0){
        holder = classroom.singleRandom();
        classroom.gradePreference(holder);
      }
     }
    //  while (classroom.getStudentList().size()>0){
    //   classroom.fullRandom();
    //  }
     System.out.println("indicator");
     ClassroomGUI gui = new ClassroomGUI(classroom, usingGridSeating);
     gui.initializeStudentInformation(classroom.getDesks());
     System.out.println("Thank you for using Seating Plus! We hope you enjoyed your experience!");
    }
    //    //***CREATE GUI OBJECT HERE that takes in appropriate parameters from the classroom 
    //    ClassroomGUI gui = new ClassroomGUI(classroom);
    //   gui.initializeStudentInformation(classroom.getDesks());
   
    
    // System.out.println("Thank you for using Seating Plus! We hope you enjoyed your experience!");

    //panel.initialize(classroom.getNumRows(), classroom.getNumCols(), classroom.getID(), classroom.getName());
  

  private static void setStudentNames(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        students.add(new Student(reader.readLine())); //names
      }
      throwIfNotEmptyLine(reader, "Error: Student name");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student names");
    }
  }
  private static void setStudentIds(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        students.get(i).setId(reader.readLine()); //ids
      }
      throwIfNotEmptyLine(reader, "Error: Student id");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student ids");
    }
  }
  private static void setStudentYears(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        students.get(i).setYear(getIntFromString(reader.readLine())); //years
      }
      throwIfNotEmptyLine(reader, "Error: Student year");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student years");
    }
  }
  private static void setStudentHeights(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        double temp = getDoubleFromString(reader.readLine());
        if (!(temp >= 10 && temp <= 110)){
          throw new Exception("Height out of range");
        }
        students.get(i).setHeight(temp); //height
      }
      throwIfNotEmptyLine(reader, "Error: Student height");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student heights");
    }
  }
  private static void setStudentMedicalFrontPreferences(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        students.get(i).setMedicalFrontPreference(getBooleanFromString(reader.readLine())); //medicalFrontPreference
      }
      throwIfNotEmptyLine(reader, "Error: Student frontmedicalpreference");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student medical front preferences");
    }
  }
  private static void setStudentMedicalBackPreferences(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        students.get(i).setMedicalBackPreference(getBooleanFromString(reader.readLine())); //medicalBackPreference
      }
      throwIfNotEmptyLine(reader, "Error: Student backmedicalpreference");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student medical front preferences");
    }
  }
  private static void setStudentGrades(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        students.get(i).setGrade(getDoubleFromString(reader.readLine())); //grade
      }
      throwIfNotEmptyLine(reader, "Error: Student grade");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student medical front preferences");
    }
  }
  private static void setStudentFriendIds(BufferedReader reader, ArrayList<Student> students, int studentCount) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        students.get(i).setFriendID(reader.readLine()); //friendID
      }
      throwIfNotEmptyLine(reader, "Error: Student friendID");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student medical front preferences");
    }
  }
  private static void setStudentWantedRows(BufferedReader reader, ArrayList<Student> students, int studentCount, int numRows) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        int temp = getIntFromString(reader.readLine());
        if (!(temp <= numRows)){
          throw new Exception("Number of rows in student preference invalid");
        }
        students.get(i).setWantedRow(temp); //wantedRow
      }
      throwIfNotEmptyLine(reader, "Error: Student wantedRow");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student medical front preferences");
    }
  }
  private static void setStudentWantedCols(BufferedReader reader, ArrayList<Student> students, int studentCount, int numCols) throws Exception{
    try {
      for (int i=0; i<studentCount; i++){
        int temp = getIntFromString(reader.readLine());
        if (!(temp <= numCols)){
          throw new Exception("Number of columns in student preference invalid");
        }
        students.get(i).setWantedCol(temp); //wantedCol
      }
      throwIfNotEmptyLine(reader, "Error: Student wantedCol");
    } catch (Exception e) {
      throw new Exception("Exception thrown when setting student medical front preferences");
    }
  }

  // Postcondition: all students in the students ArrayList have been assigned their respective attributes from the text file in the grid mode
  private static void setStudentAttributesGrid(BufferedReader reader, ArrayList<Student> students, int studentCount, int numRows, int numCols) throws Exception {
    setStudentNames(reader, students, studentCount);
    setStudentIds(reader, students, studentCount);
    setStudentYears(reader, students, studentCount);
    setStudentHeights(reader, students, studentCount);
    setStudentMedicalFrontPreferences(reader, students, studentCount);
    setStudentMedicalBackPreferences(reader, students, studentCount); 
    setStudentGrades(reader, students, studentCount); 
    setStudentFriendIds(reader, students, studentCount); 
    setStudentWantedRows(reader, students, studentCount, numRows); 
    setStudentWantedCols(reader, students, studentCount, numCols); 
  }

  //NOT DONE
  private static void setStudentAttributesGroup(BufferedReader reader, ArrayList<Student> students, int studentCount, int numRows, int numCols, int numInGroup) throws Exception {
    setStudentNames(reader, students, studentCount);
    setStudentIds(reader, students, studentCount);
    setStudentYears(reader, students, studentCount);
    setStudentHeights(reader, students, studentCount);
    setStudentMedicalFrontPreferences(reader, students, studentCount);
    setStudentMedicalBackPreferences(reader, students, studentCount); 
    setStudentGrades(reader, students, studentCount); 
    setStudentFriendIds(reader, students, studentCount); 
    setStudentWantedRows(reader, students, studentCount, numRows); 
    setStudentWantedCols(reader, students, studentCount, numCols); 
  }

  // Postcondition: first numerical digits of string returned as int
  private static int getIntFromString(String s){
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

  // Postcondition: first numerical digits (including decimal point) of string returned as double
  private static double getDoubleFromString(String s){
    String temp = "";
    for (int i=0; i<s.length(); i++){
      char tempChar = s.charAt(i);
      if(Character.isDigit(tempChar) || tempChar == '.'){
        temp += tempChar;
      }
      else {
        break;
      }
    }
    return Double.parseDouble(temp);
  }

  // Postcondition: returns true if yes/true and returns false if no/false
  private static boolean getBooleanFromString(String s) throws Exception {
    s = s.toLowerCase();
    if ((s.length()==4 && s.substring(0, 4).equals("true")) || (s.length()==3 && s.substring(0,3).equals("yes"))){
      return true;
    }
    else if ((s.length()==5 && s.substring(0, 5).equals("false")) || (s.length()==2 && s.substring(0, 2).equals("no"))) {
      return false;
    }
    throw new Exception();
  }

  // Postcondition: returns true if current line is empty
  private static boolean throwIfNotEmptyLine(BufferedReader reader, String errorMessage) throws Exception {
    if (!reader.readLine().isEmpty()){
      throw new Exception(errorMessage);
    }
    return true;
  }

  // Postcondition: returns 2D Desk array of Desks that are either null or initialized, but they will NOT contain any attributes
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
 
