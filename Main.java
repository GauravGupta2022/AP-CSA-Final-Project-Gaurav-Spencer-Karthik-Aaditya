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
     
    boolean usingGridSeating = true; //false = group seating
    final String textFileName = "GroupSeating.txt";

    FileReaderClass reader = new FileReaderClass(textFileName, usingGridSeating);
    Classroom classroom = reader.parse();

     ///START OF PROGRAM
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
     String response = input.next();
     response = input.nextLine();
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
    //    //***CREATE GUI OBJECT HERE that takes in appropriate parameters from the classroom 
    //    ClassroomGUI gui = new ClassroomGUI(classroom);
    //   gui.initializeStudentInformation(classroom.getDesks());
   
   System.out.println("Thank you for using Seating Plus! We hope you enjoyed your experience!");
      if (response.contains("3")){//seat by similar GPA
       while (classroom.getStudentList().size()>0){
         classroom.gradePreference(classroom.singleRandom());
       }
      }
      // while (classroom.getStudentList().size()>0){
      //  classroom.fullRandom();
      // }
      ClassroomGUI gui = new ClassroomGUI(classroom, usingGridSeating);
      gui.initializeStudentInformation(classroom.getDesks());
      else if (response.contains("3")){//seat by similar GPA
       while (classroom.getStudentList().size()>0){
         String holder = classroom.singleRandom();
         classroom.gradePreference(holder);
       }
      }
      gui.initializeStudentInformation(classroom.getDesks());
      System.out.println("Thank you for using Seating Plus! We hope you enjoyed your experience!");
     }
 }