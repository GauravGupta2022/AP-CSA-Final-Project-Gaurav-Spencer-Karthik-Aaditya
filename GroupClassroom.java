import java.util.ArrayList;

public class GroupClassroom extends Classroom{
    private int numInGroup = 0;
    public GroupClassroom(){
        super();
        this.numInGroup = 0;
    }
    public GroupClassroom(int deskCount, int studentCount, Desk[][] desks, String roomNumber, int periodNumber, ArrayList<Student> students, int numInGroup){
        this.deskCount = deskCount;
        this.studentCount = studentCount;
        this.desks = desks;
        this.numRows = desks.length;
        this.numCols = desks[0].length;
        this.roomNumber = roomNumber;
        this.periodNumber = periodNumber;
        this.studentList = students;
        this.numInGroup = numInGroup;
    }
    public void groupChoosing(){
        medicalBackPreference();
        medicalFrontPreference();
        //takes first person in student list, places him in group, takes his friends (if they exist) and places them in group
        //if no friends, then skip the group until the others are filled and then randomize it
        
        for(int r = 0; r < desks.length; r++){
          for(int c = 0; c < desks[0].length; c++){
            if(desks[r][c] != null && desks[r][c].getOccupied() == false && desks[r][c].getChecked() == false){
              if(studentList.size() > 0){
                Student s = studentList.remove(0);
                desks[r][c].seat(s);
                
               
       
                
                int x = r;
                int y = c;
                boolean flag = true;
                int count = 0;
                  while(true){
                    if (x < 0 || x >= desks.length || y < 0 || y >= desks[0].length) {
                      break;
                  }
                    if(desks[x][y] != null && s.getGroupMembers().size() > 0 && desks[x][y].getOccupied() == false ){
                      desks[x][y].seat(findStudent(s.getGroupMembers().remove(0)));
                        count++;
                      if(flag == true){
                        y++;
                        
                      }
                      else{
                        
                        y--;
                      }
                      
                    }
                    else if(desks[x][y] == null && flag == false){
                      if(count > numInGroup){
                        break;
                      }
                      x++;
                      y--;
                      
                      flag = !flag;

                    

                    }
                    else if(desks[x][y] == null && flag == true){
                      if(count > numInGroup){
                        break;
                      }
                      x++;
                      y++;
                      
                      flag = !flag;

                    }
                    else if(s.getGroupMembers().size() <= 0){
                        break;
                    }
                    
                  }
                


              }
            }
            
          }
        }
        //code to randomize remaining seats
        fullRandom();
      }
      public String isNearbyDeskEmpty(int r, int c){
        String result = "";
        if(desks[r][c] != null){
          if(r > 0 && r != desks.length-2 && c > 0 && c != desks[0].length -2){
            if((desks[r][c+1] != null && desks[r][c+1].getOccupied() == false )){
              result = "Right";

            }
            else if((desks[r][c-1] != null && desks[r][c-1].getOccupied() == false )){
              result = "Left";
            }
            else if((desks[r-1][c] != null && desks[r-1][c].getOccupied() == false )){
              result = "Up";
            }
            else if((desks[r+1][c] != null && desks[r+1][c].getOccupied() == false )){
              result = "Down";
            }
          }
          else if((r == 0) || (r== desks.length - 1)){
            if((desks[r][c+1] != null && desks[r][c+1].getOccupied() == false )){
              result = "Right";

            }
            else if((desks[r][c-1] != null && desks[r][c-1].getOccupied() == false )){
              result = "Left";
            }

          }
          else if((c == 0 ) || (c == desks[0].length - 1)){
             if((desks[r-1][c] != null && desks[r-1][c].getOccupied() == false )){
              result = "Up";
            }
            else if((desks[r+1][c] != null && desks[r+1][c].getOccupied() == false )){
              result = "Down";
            }
        }
        
       
      }
          return result;
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
} 
}
