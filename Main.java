import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
      
      //CHANGE THIS LINE IF YOU WANT TO CHANGE THE TEXT FILE BEING READ AND THEREFORE THE MODE
      final String textFileName = "GridSeating.txt"; 

      FileReaderClass reader = new FileReaderClass(textFileName);
      Classroom classroom = reader.parse(); //complete classroom including arraylist of students
  
      System.out.println("Welcome to Seating Plus!");
      Scanner input = new Scanner(System.in);
      System.out.println("Hello, would you like to get a completely randomized desk setup [1], let your students choose their own desks [2], or do you want to seat students by GPA [3]");
      String response = input.nextLine();
      
      if (response.contains("1")){ //completely randomized
        classroom.medicalFrontPreference();
        classroom.medicalBackPreference();
        classroom.fullRandom();
      }
      
      else if (response.contains("2")) { //students choose own desks
        classroom.medicalFrontPreference();
        classroom.medicalBackPreference();
        if (classroom.getUsingGridSeating()){
          while (classroom.getStudentList().size()>1){
            System.out.println("While Loop Execution");
            String s = classroom.singleRandom();
            classroom.personalPreference(s);
            System.out.println(classroom.getStudentList().size());
          }
        }
        else {
          //code with group
          String groupResponse = input.next();
          System.out.println("Press [1] to allow students to choose only one friend, press [2] to allow students to choose all friends");;
          if (groupResponse.equals("1")){
            while (classroom.getStudentList().size()>0){
              classroom.personalPreference(classroom.singleRandom());
            }
          }
          else {
            //code with CHOOSE ALL group  
            classroom.groupChoosing();
          }
        }
      }
      
      else if (response.contains("3")){ //seat by similar GPA (elite seating)
        classroom.medicalFrontPreference();
        classroom.medicalBackPreference();
        classroom.eliteSeating(50.0);
        classroom.fullRandom();
        }
      
      ClassroomGUI gui = new ClassroomGUI(classroom, classroom.getUsingGridSeating());
      gui.initializeStudentInformation(classroom.getDesks());
      System.out.println("Thank you for using Seating Plus! We hope you enjoyed your experience!");
      input.close();
    } //END OF MAIN METHOD

}
 
