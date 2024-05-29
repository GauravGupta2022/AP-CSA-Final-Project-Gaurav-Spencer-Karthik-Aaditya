
import java.util.ArrayList;
import java.lang.Math;
import java.util.Iterator;

public class Classroom{
  
    protected int deskCount;
    protected int studentCount;
    protected Desk[][] desks;
    protected int numRows;
    protected int numCols;
    protected String roomNumber;
    protected int periodNumber;
    protected boolean isGroup = false;
    protected ArrayList<Student> studentList = new ArrayList<Student>();
    protected boolean usingGridSeating; //true = grid mode

    public void setUsingGridSeating(boolean usingGridSeating){
      this.usingGridSeating = usingGridSeating;
    }
    public void setIsGroup(boolean b){
      isGroup = b;
    }
    public void setDeskCount(int deskCount) {
      this.deskCount = deskCount;
    }
    public void setStudentCount(int studentCount) {
      this.studentCount = studentCount;
    }
    public void setDesks(Desk[][] desks) {
      this.desks = desks;
    }
    public void setNumRows(int numRows) {
      this.numRows = numRows;
    }
    public void setNumCols(int numCols) {
      this.numCols = numCols;
    }
    public void setRoomNumber(String roomNumber) {
      this.roomNumber = roomNumber;
    }
    public void setPeriodNumber(int periodNumber) {
      this.periodNumber = periodNumber;
    }
    public void setStudentList(ArrayList<Student> studentList) {
      this.studentList = studentList;
    }
    public boolean getUsingGridSeating(){
      return usingGridSeating;
    }
    public int getDeskCount() {
      return deskCount;
    }
    public int getStudentCount() {
      return studentCount;
    }
    public Desk[][] getDesks() {
      return desks;
    }
    public int getNumRows() {
      return numRows;
    }
    public int getNumCols() {
      return numCols;
    }
    public String getRoomNumber() {
      return roomNumber;
    }
    public int getPeriodNumber() {
      return periodNumber;
    }
    public ArrayList<Student> getStudentList() {
      return studentList;
    }

    public Classroom (int deskCount, int studentCount, Desk[][] desks, String roomNumber, int periodNumber, ArrayList<Student> students){
      this.deskCount = deskCount;
      this.studentCount = studentCount;
      this.desks = desks;
      numRows = desks.length;
      numCols = desks[0].length;
      this.roomNumber = roomNumber;
      this.periodNumber = periodNumber;
      this.studentList = students;
    }
    public Classroom(){
      this.deskCount = 0;
      this.studentCount = 0;
      this.desks = new Desk[0][0];
      this.roomNumber = "";
      this.periodNumber = 1;
      this.studentList = new ArrayList<Student>();
    }
    public void setDesks(){
      int x = 0;
    }
    public void reset(){
      for(int r = 0; r < desks.length; r++){
        for(int c = 0; c < desks[0].length; c++){
          if(desks[r][c] != null){
            studentList.add(desks[r][c].unseat());

          }
        }
      }
    }
    public void fullRandom(){//Creates a fully randomized seating chart
      int rand = 0;
      for(int r = 0; r < desks.length; r++){
        for(int c = 0; c < desks[0].length; c++){
          //code to install student
          if(desks[r][c]!= null && desks[r][c].getOccupied() == false){
            if(studentList.size()>0){
            rand = (int)Math.random()*studentList.size();
            desks[r][c].seat(studentList.get(rand));
            studentList.remove(rand);
            }
          }
        }
      }

    }
   /* public void eliteSeating(double min){
      
      ArrayList<Student> newList = new ArrayList<Student>();
      for (int i=0;i<studentList.size();i++){
        if (studentList.get(i).getGrade()>=min){
          newList.add(studentList.get(i));
          studentList.remove(i);
        }
      }
      int count = 0;
      while (count<newList.size()){
        System.out.println("loop has been ran");
        int row = newList.get(count).getWantedRow()-1;
        int col = newList.get(count).getWantedCol()-1;
        if(desks[row][col]!=null && desks[row][col].getOccupied() == false && !isNearbyDeskEmpty(row,col).equals("")){
          desks[row][col].seat(newList.get(count));
          System.out.println("seated");

          String dir = isNearbyDeskEmpty(row, col);
          if(dir.equals("Right") && desks[row][col+1]!=null){
            desks[row][col+1].seat(findStudent(newList.get(count).getFriendID()));
            System.out.println("r"); 
          }
          else if(dir.equals("Left") && desks[row][col-1]!=null){
            desks[row][col-1].seat(findStudent(newList.get(count).getFriendID()));

            System.out.println("l");
          }
          else if(dir.equals("Up") && desks[row-1][col]!=null){
            desks[row-1][col].seat(findStudent(newList.get(count).getFriendID()));

            System.out.println("u");
          }
          else if (dir.equals("Down") && desks[row+1][col]!=null){
            desks[row+1][col].seat(findStudent(newList.get(count).getFriendID()));

            System.out.println("d");
          }
          count++;
          //S Cleanup [possible print statement?]
          }
          else{
            System.out.println("spot taken!");
            boolean flag = true;

            int x = 0;
            int y = 0;
            String dir = "";
            while(flag){
              row = (int)(Math.random()*desks.length-1)+1;
              col = (int)(Math.random()*desks[0].length-1)+1;
              if(desks[row][col]!=null && desks[row][col].getOccupied() == false && !isNearbyDeskEmpty(row,col).equals("")){
                flag = false;
                desks[row][col].seat(newList.get(count));

                System.out.println("seated in new pos");
                dir = isNearbyDeskEmpty(row, col);
                x = row;
                y = col;
              } 

              
              
          }

          if(dir.equals("Right") && desks[x][y+1]!=null){
            desks[x][y+1].seat(findStudent(newList.get(count).getFriendID()));
            System.out.println("r"); 
          }
          else if(dir.equals("Left") && desks[x][y-1]!=null){
            desks[x][y-1].seat(findStudent(newList.get(count).getFriendID()));
            System.out.println("l");
          }
          else if(dir.equals("Up") && desks[x-1][y]!=null){
            desks[x-1][y].seat(findStudent(newList.get(count).getFriendID()));
            System.out.println("u");

          }
          else if (dir.equals("Down") && desks[x+1][y]!=null){
            desks[x+1][y].seat(findStudent(newList.get(count).getFriendID()));
            System.out.println("d");
          }
          count++;
        }
      }
    }
    */
      
//     public void eliteSeating(double min) { [Karthik 6:45 PM method]
//     ArrayList<Student> newList = new ArrayList<Student>();

//     // Iterate over the studentList using an iterator to safely remove elements
//     Iterator<Student> iterator = studentList.iterator();
//     while (iterator.hasNext()) {
//         Student student = iterator.next();
//         if (student.getGrade() >= min) {
//             newList.add(student);
//             iterator.remove();
//         }
//     }

//     int count = 0;
//     while (count < newList.size()) {
//         System.out.println("loop has been ran");
//         int row = newList.get(count).getWantedRow() - 1;
//         int col = newList.get(count).getWantedCol() - 1;

//         if (row >= 0 && col >= 0 && row < desks.length && col < desks[0].length &&
//             desks[row][col] != null && !desks[row][col].getOccupied() && isNearbyDeskEmpty(row, col).equals("")) {
            
//             desks[row][col].seat(newList.get(count));
//             System.out.println("seated");

//             String dir = isNearbyDeskEmpty(row, col);
//             if (dir.equals("Right") && col + 1 < desks[0].length && desks[row][col + 1] != null) {
//                 desks[row][col + 1].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("r");
//             } else if (dir.equals("Left") && col - 1 >= 0 && desks[row][col - 1] != null) {
//                 desks[row][col - 1].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("l");
//             } else if (dir.equals("Up") && row - 1 >= 0 && desks[row - 1][col] != null) {
//                 desks[row - 1][col].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("u");
//             } else if (dir.equals("Down") && row + 1 < desks.length && desks[row + 1][col] != null) {
//                 desks[row + 1][col].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("d");
//             }
//             count++;
//         } 
//         else {
//             System.out.println("spot taken!");
//             boolean flag = true;
//             int x = 0;
//             int y = 0;
//             String dir = "";

//             while (flag) {
//                 row = (int) (Math.random() * desks.length);
//                 col = (int) (Math.random() * desks[0].length);

//                 if (row >= 0 && col >= 0 && row < desks.length && col < desks[0].length &&
//                     desks[row][col] != null && !desks[row][col].getOccupied() && isNearbyDeskEmpty(row, col).equals("")) {
                    
//                     flag = false;
//                     desks[row][col].seat(newList.get(count));
//                     System.out.println("seated in new pos");
//                     dir = isNearbyDeskEmpty(row, col);
//                     x = row;
//                     y = col;
//                 }
//             }

//             if (dir.equals("Right") && y + 1 < desks[0].length && desks[x][y + 1] != null) {
//                 desks[x][y + 1].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("r");
//             } else if (dir.equals("Left") && y - 1 >= 0 && desks[x][y - 1] != null) {
//                 desks[x][y - 1].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("l");
//             } else if (dir.equals("Up") && x - 1 >= 0 && desks[x - 1][y] != null) {
//                 desks[x - 1][y].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("u");
//             } else if (dir.equals("Down") && x + 1 < desks.length && desks[x + 1][y] != null) {
//                 desks[x + 1][y].seat(findStudent(newList.get(count).getFriendID()));
//                 System.out.println("d");
//             }
//             count++;
//         }
//     }
//     System.out.println("finished");

        public void eliteSeating(double min) {
    ArrayList<Student> newList = new ArrayList<>();

    // Collect students with GPA above the minimum
    for (int i = 0; i < studentList.size(); i++) {
        if (studentList.get(i).getGrade() >= min) {
            newList.add(studentList.get(i));
        }
    }
    System.out.println("elite students found");

    // Remove collected students from the original list
    studentList.removeAll(newList);

    // Iterate through the list of elite students
    for (Student student : newList) {
        boolean seated = false;
        int row = student.getWantedRow() - 1;
        int col = student.getWantedCol() - 1;

        if (isValidSeat(row, col) && desks[row][col].getOccupied() == false && !isNearbyDeskEmpty(row, col).equals("")) {
            desks[row][col].seat(student);
            seatFriend(row, col, student.getFriendID());
            seated = true;
            System.out.println("seated");
        } else {
            // Try to find a random seat
            while (!seated) {
                row = (int) (Math.random() * desks.length);
                col = (int) (Math.random() * desks[0].length);
                if (isValidSeat(row, col) && desks[row][col].getOccupied() == false && !isNearbyDeskEmpty(row, col).equals("")) {
                    desks[row][col].seat(student);
                    seatFriend(row, col, student.getFriendID());
                    seated = true;
                    System.out.println("seated in new pos");
                }
            }
        }
    }

    System.out.println("finished");
}

private boolean isValidSeat(int row, int col) {
    return row >= 0 && row < desks.length && col >= 0 && col < desks[0].length && desks[row][col] != null;
}

private void seatFriend(int row, int col, String friendID) {
    String dir = isNearbyDeskEmpty(row, col);
    Student friend = findStudent(""+ friendID);

    if (dir.equals("Right") && isValidSeat(row, col + 1)) {
        desks[row][col + 1].seat(friend);
    } else if (dir.equals("Left") && isValidSeat(row, col - 1)) {
        desks[row][col - 1].seat(friend);
    } else if (dir.equals("Up") && isValidSeat(row - 1, col)) {
        desks[row - 1][col].seat(friend);
    } else if (dir.equals("Down") && isValidSeat(row + 1, col)) {
        desks[row + 1][col].seat(friend);
    }
}

      
      
      
      
      
      
      
    public void medicalBackPreference(){//Seats kids (with medical needs for it) at the front of the classroom
      ArrayList<Student> newList = new ArrayList<Student>();
      for (int i=0; i<studentList.size();i++){
        
        if (studentList.get(i).getMedicalFrontPreference()==true){//change var name
          newList.add(studentList.get(i));
          studentList.remove(i);
          i--;
        }
      }
      int count = 0;
      for(int r = desks.length-1; r > -1; r--){
        for(int c = 0; c< desks[0].length; c++){
          //System.out.println("e");
          if(desks[r][c]!= null && count < newList.size()){
              desks[r][c].seat(newList.get(count));
              count++;
            }
          }
        }
      }
      public void medicalFrontPreference(){//Seats kids (with medical needs for it) at the back of the classroom
        ArrayList<Student> newList = new ArrayList<Student>();
        for (int i=0;i<studentList.size();i++){
          if (studentList.get(i).getMedicalBackPreference()==true){//change var name
            newList.add(studentList.get(i));
            studentList.remove(i);
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
    public String singleRandom(){ //Assigns a random student to one desk
        int rand = 0;
        for (int r = 0; r < desks.length; r++){
            for (int c = 0; c < desks[0].length; c++){
                if (desks[r][c]!=null && desks[r][c].getOccupied()== false){
                    if (studentList.size()>0){
                        rand = (int)(Math.random()*studentList.size());
                        Student holder = studentList.get(rand);
                        desks[r][c].seat(studentList.get(rand));
                        studentList.remove(rand);
                        System.out.println(studentList.size());
                        System.out.println("f");
                        return holder.getID();
                    }
                }
            }
            
        }
        return null;
      }


      public void medicalWithRandom(){
        medicalBackPreference();
        medicalFrontPreference();
        fullRandom();
      }
      
        public Student findStudentWithGrade(double num){ //Finds a student in the grade of the num parameter
        for (int i=0;i<studentList.size();i++){
          if (Math.abs(studentList.get(i).getGrade()-num)<= 0.5){
            return studentList.get(i);
          }
        }
        return null;
      }
      public void gradePreference(String soughtID){ //Seats one student with the same grade next to one another
        double currGrade = 0;
        for (int row=0;row<desks.length;row++){
          for (int col=0;col<desks[0].length; col++){
            if (desks[row][col]!=null && desks[row][col].getStudent().getID().equals(soughtID) && desks[row][col].getOccupied() && !isNearbyDeskEmpty(row, col).equals("")){
              currGrade = desks[row][col].getStudent().getGrade();
              System.out.println("SpencerIndex");
              if(isGroup == true){
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
              else{
                if (isNearbyDeskEmpty(row, col).equals("Right") && (col+2)<desks[0].length){
                desks[row][col+2].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Left") && (col-2)>-2){
                desks[row][col-2].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Down") && (row+2)<desks.length){
                desks[row+2][col].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
              else if (isNearbyDeskEmpty(row,col).equals("Up") && (row-2)>-1){
                desks[row-2][col].seat(findStudentWithGrade(currGrade));
                studentList.remove(findStudentWithGrade(currGrade));
                break;
              }
                
            }
          }
          break;
        }
      }
      }
      public void personalPreference(String soughtID){ //Seats one student (who was selected by the original student) next to one another
        String currFriendID = "";
        for (int row=0;row<desks.length;row++){
          for (int col=0;col<desks[0].length;col++){
            if (desks[row][col]!=null && desks[row][col].getStudent().getID().equals(soughtID) &&desks[row][col].getOccupied() && !isNearbyDeskEmpty(row, col).equals("")){
              currFriendID = desks[row][col].getStudent().getFriendID();
            
              if (isNearbyDeskEmpty(row, col).equals("Right") && (col+2)<desks[0].length){
                desks[row][col+2].seat(findStudent(currFriendID));
                studentList.remove(findStudent(currFriendID));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Left") && (col-2)>-1){
                desks[row][col-2].seat(findStudent(currFriendID));
                studentList.remove(findStudent(currFriendID));
                break;
              }
              else if (isNearbyDeskEmpty(row, col).equals("Down") && (row+2)<desks.length){
                desks[row+2][col].seat(findStudent(currFriendID));
                studentList.remove(findStudent(currFriendID));
                break;
              }
              else if (isNearbyDeskEmpty(row,col).equals("Up") && (row-2)>-1){
                desks[row-2][col].seat(findStudent(currFriendID));
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
      

public void groupChoosing(){
  System.out.println("e");
}
public String isNearbyDeskEmpty(int r, int c) {
  if (desks[r][c] == null) {
      return "";
  }

  
  boolean canCheckRight = c + 2 < desks[0].length;
  boolean canCheckLeft = c - 2 >= 0;
  boolean canCheckUp = r - 2 >= 0;
  boolean canCheckDown = r + 2 < desks.length;

  if (canCheckRight && desks[r][c+2] != null && !desks[r][c+2].getOccupied() && desks[r][c+1] == null) {
      return "Right";
  }
  if (canCheckLeft && desks[r][c-2] != null && !desks[r][c-2].getOccupied() && desks[r][c-1] == null) {
      return "Left";
  }
  if (canCheckUp && desks[r-2][c] != null && !desks[r-2][c].getOccupied() && desks[r-1][c] == null) {
      return "Up";
  }
  if (canCheckDown && desks[r+2][c] != null && !desks[r+2][c].getOccupied() && desks[r+1][c] == null) {
      return "Down";
  }

  return "";
}

  

}

  
