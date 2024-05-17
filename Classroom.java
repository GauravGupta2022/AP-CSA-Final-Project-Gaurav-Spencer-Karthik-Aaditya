
import java.util.ArrayList;
import java.lang.Math;
public class Classroom{
    private int deskCount;
    private int studentCount;
    private Desk[][] desks = new Desk[20][20];
    private int classNumber;
    private int periodNumber;
    private ArrayList<Student> studentList = new ArrayList<Student>();
    public Classroom (int deskCount, int studentCount, Desk[][] desks, int classNumber, int periodNumber, ArrayList<Student> students){
      this.deskCount = deskCount;
      this.studentCount = studentCount;
      this.desks = desks;
      this.classNumber = classNumber;
      this.periodNumber = periodNumber;
      this.studentList = students;
    }
    public Classroom(){
      this.deskCount = 0;
      this.studentCount = 0;
      this.desks = new Desk[0][0];
      this.classNumber = 0;
      this.periodNumber = 1;
      this.studentList = new ArrayList<Student>();
    }
    public void setDesks(){
      int x = 0;
    }
    public void fullRandom(){//Creates a fully randomized seating chart
      int rand = 0;
      for(int r = 0; r < desks.length; r++){
        for(int c = 0; c < desks[0].length; c++){
          //code to install student
          if(desks[r][c]!= null){
            if(studentList.size()>0){
            rand = (int)Math.random()*studentList.size();
            desks[r][c].seat(studentList.get(rand));
            studentList.remove(rand);
            }
          }
        }
      }

    }
    public void medicalFrontPreference(){//Seats kids (with medical needs for it) at the front of the classroom
      ArrayList<Student> newList = new ArrayList<Student>();
      for (int i=0; i<studentList.size();i++){
        if (studentList.get(i).getMedicalFrontPreference()==true){//change var name
          newList.add(studentList.get(i));
        }
      }
      int count = 0;
      for(int r = desks.length-1; r > -1; r--){
        for(int c = 0; c< desks[0].length; c++){
          if(desks[r][c]!= null && count < newList.size()){
              desks[r][c].seat(newList.get(count));
              count++;
            }
          }
        }
      }
      public void medicalBackPreference(){//Seats kids (with medical needs for it) at the back of the classroom
        ArrayList<Student> newList = new ArrayList<Student>();
        for (int i=0;i<studentList.size();i++){
          if (studentList.get(i).getMedicalBackPreference()==true){//change var name
            newList.add(studentList.get(i));
          }
          int count = 0;
          for (int p=0;p<desks.length;p++){
            for (int o=0;o<desks[0].length;o++){
              if (desks[p][o]!=null && count < newList.size()){
                desks[p][o].seat(newList.get(count));
                count++;
              }
            }
          }
        }
      }
    public void singleRandom(){ //Assigns a random student to one desk
        int rand = 0;
        for (int r = 0; r < desks.length; r++){
            for (int c = 0; c < desks[0].length; c++){
                if (desks[r][c]!=null){
                    if (studentList.size()>0){
                        rand = (int)Math.random()*studentList.size();
                        desks[r][c].seat(studentList.get(rand));
                        studentList.remove(rand);
                        break;
                    }
                }
            }
            break;
        }
      }
      public Student findStudentWithGrade(double num){ //Finds a student in the grade of the num parameter
        for (int i=0;i<studentList.size();i++){
          if (studentList.get(i).getGrade()==num){
            return studentList.get(i);
          }
        }
        return null;
      }
      public void gradePreference(){ //Seats one student with the same grade next to one another
        double currGrade = 0;
        for (int row=0;row<desks.length;row++){
          for (int col=0;col<desks[0].length;col++){
            if (desks[row][col]!=null && desks[row][col].getOccupied() && !isNearbyDeskEmpty(row, col).equals("")){
              currGrade = desks[row][col].getStudent().getGrade();
              if (isNearbyDeskEmpty(row, col).equals("Right") && (col+1)<desks[0].length){
                desks[row][col+1].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Left") && (col-1)>-1){
                desks[row][col-1].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Down") && (row+1)<desks.length){
                desks[row+1][col].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
              else if (isNearbyDeskEmpty(row,col).equals("Up") && (row-1)>-1){
                desks[row-1][col].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
            }
          }
          break;
        }
      }
      public void personalPreference(){ //Seats one student (who was  selected by the original student) next to one another
        String currFriendID = "";
        for (int row=0;row<desks.length;row++){
          for (int col=0;col<desks[0].length;col++){
            if (desks[row][col]!=null && desks[row][col].getOccupied() && !isNearbyDeskEmpty(row, col).equals("")){
              currFriendID = desks[row][col].getStudent().getFriendID();
              if (isNearbyDeskEmpty(row, col).equals("Right") && (col+1)<desks[0].length){
                desks[row][col+1].seat(findStudent(currFriendID));
                studentList.remove(findStudent(currFriendID));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Left") && (col-1)>-1){
                desks[row][col-1].seat(findStudent(currFriendID));
                studentList.remove(findStudent(currFriendID));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Down") && (row+1)<desks.length){
                desks[row+1][col].seat(findStudent(currFriendID));
                studentList.remove(findStudent(currFriendID));
                break;
              }
              else if (isNearbyDeskEmpty(row,col).equals("Up") && (row-1)>-1){
                desks[row-1][col].seat(findStudent(currFriendID));
                studentList.remove(findStudent(currFriendID));
                break;
              }
            }
          }
          break;
        }
      }
      public Student findStudent(String givenID){ //Finds a student with a given ID number
        for (int i=0;i<studentList.size();i++){
          if (studentList.get(i).getID().equals(givenID)){
            return studentList.get(i);
          }
        }
        return null;
      }
      public String isNearbyDeskEmpty(int r, int c){
        String result = "";
        if(desks[r][c] != null){
          if(r > 1 && r != desks.length-2 && c > 1 && c != desks[0].length -2){
            if((desks[r][c+2].getOccupied() == false && desks[r][c+1] == null) || (desks[r][c+1] != null && desks[r][c+1].getOccupied() == false )){
              result = "Right";

            }
            else if((desks[r][c-2].getOccupied() == false && desks[r][c-1] == null) || (desks[r][c-1] != null && desks[r][c-1].getOccupied() == false )){
              result = "Left";
            }
            else if((desks[r-2][c].getOccupied() == false && desks[r-1][c] == null) || (desks[r-1][c] != null && desks[r-1][c].getOccupied() == false )){
              result = "Up";
            }
            else if((desks[r+2][c].getOccupied() == false && desks[r+1][c] == null) || (desks[r+1][c] != null && desks[r+1][c].getOccupied() == false )){
              result = "Down";
            }
          }
          else if((r == 0 || r == 1) || (r== desks.length - 1 || r == desks.length - 2)){
            if((desks[r][c+2].getOccupied() == false && desks[r][c+1] == null) || (desks[r][c+1] != null && desks[r][c+1].getOccupied() == false )){
              result = "Right";

            }
            else if((desks[r][c-2].getOccupied() == false && desks[r][c-1] == null) || (desks[r][c-1] != null && desks[r][c-1].getOccupied() == false )){
              result = "Left";
            }

          }
          else if((c == 0 || c ==1) || (c == desks[0].length - 1 || c == desks.length -2)){
             if((desks[r-2][c].getOccupied() == false && desks[r-1][c] == null) || (desks[r-1][c] != null && desks[r-1][c].getOccupied() == false )){
              result = "Up";
            }
            else if((desks[r+2][c].getOccupied() == false && desks[r+1][c] == null) || (desks[r+1][c] != null && desks[r+1][c].getOccupied() == false )){
              result = "Down";
            }
        }
        
       
      }
          return result;
} 

}
