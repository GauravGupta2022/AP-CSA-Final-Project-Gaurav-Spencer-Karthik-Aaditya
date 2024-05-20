import java.util.ArrayList;
public class Student {
   private final double grade; // grade in the class
   private final int YEAR; // gradelevel
   private final String NAME;
   private final double HEIGHT;
   private final String ID;
   private boolean medicalFrontPreference;
   private boolean medicalBackPreference;
   private String friendID;
   private int wantedRow;
   private int wantedCol;
   private ArrayList<String> groupMembers = new ArrayList<String>();
   public Student() {
     grade = 0.0;
     YEAR = 9;
     NAME = "X";
     HEIGHT = 0.0;
     ID = "000000";
     medicalFrontPreference = false;
     medicalBackPreference = false;
     friendID = "000000";
     wantedRow = -1;
     wantedCol = -1;
   }
   public int getWantedRow(){
    return wantedRow;
   }
   public int getWantedCol(){
    return wantedCol;
   }
   public void setWantedRow(int wantedRow){
    this.wantedRow = wantedRow;
   }
   public void setWantedCol(int wantedCol){
    this.wantedCol = wantedCol;
   }
 
   public Student(double grade, int year, String name, double height, String id, boolean medicalFrontPreference, boolean medicalBackPreference) {
     this.grade = grade;
     this.YEAR = year;
     this.NAME = name;
     this.HEIGHT = height;
     this.ID = id;
     this.medicalFrontPreference = medicalFrontPreference;
     this.medicalBackPreference = medicalBackPreference;
     this.friendID = friendID;
     this.wantedRow = wantedRow;
     this.wantedCol = wantedCol;
   }

   public Student(double grade, int year, String name, double height, String id, boolean medicalFrontPreference, boolean medicalBackPreference, ArrayList<String> group) {
    this.grade = grade;
    this.YEAR = year;
    this.NAME = name;
    this.HEIGHT = height;
    this.ID = id;
    this.medicalFrontPreference = medicalFrontPreference;
    this.medicalBackPreference = medicalBackPreference;
    this.friendID = friendID;
    this.wantedRow = wantedRow;
    this.wantedCol = wantedCol;
    groupMembers = group;
  }
 
   public boolean getMedicalFrontPreference(){
    return medicalFrontPreference;
   }

   public boolean getMedicalBackPreference(){
    return medicalBackPreference;
   }

   public double getGrade() {
     return grade;
   }
 
   public int getYear() {
     return YEAR;
   }
   public String getName(){
     return NAME;
   }
   public double getHeight(){
     return HEIGHT;
   }
   public String getID(){
     return ID;
   }
   public String getFriendID(){
    return friendID;
   }
   public String toString(){
    String toReturn = "";
    toReturn = toReturn.concat("Grade: "+this.grade+" - ");
    toReturn = toReturn.concat("YEAR: "+this.YEAR+" - ");
    toReturn = toReturn.concat("NAME: "+this.NAME+" - ");
    toReturn = toReturn.concat("HEIGHT: "+this.HEIGHT+" - ");
    toReturn = toReturn.concat("ID: "+this.ID+" - ");
    toReturn = toReturn.concat("medicalFrontPreference: "+this.medicalFrontPreference+" - ");
    toReturn = toReturn.concat("medicalBackPreference: "+this.medicalBackPreference+" - ");
    toReturn = toReturn.concat("friendID: "+this.friendID);
    return toReturn;
   }
 }
