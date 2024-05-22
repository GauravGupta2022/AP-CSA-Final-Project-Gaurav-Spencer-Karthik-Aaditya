import java.util.ArrayList;
public class Student {
   private double grade; // grade in the class
   private int year; // gradelevel
   private String name;
   private double height;
   private String id;
   private boolean medicalFrontPreference;
   private boolean medicalBackPreference;
   private String friendID;
   private int wantedRow;
   private int wantedCol;
   private ArrayList<String> groupMembers = new ArrayList<String>();
   public Student() {
     grade = 0.0;
     year = 9;
     name = "X";
     height = 0.0;
     id = "000000";
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
     this.year = year;
     this.name = name;
     this.height = height;
     this.id = id;
     this.medicalFrontPreference = medicalFrontPreference;
     this.medicalBackPreference = medicalBackPreference;
     this.friendID = friendID;
     this.wantedRow = wantedRow;
     this.wantedCol = wantedCol;
   }

   public Student(String name){
      this.name = name;
      grade = 0.0;
      year = 0;
      height = 0.0;
      id = "000000";
      medicalFrontPreference = false;
      medicalBackPreference = false;
      friendID = "000000";
      wantedRow = -1;
      wantedCol = -1;
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
     return year;
   }
   public String getName(){
     return name;
   }
   public double getHeight(){
     return height;
   }
   public String getID(){
     return id;
   }
   public String getFriendID(){
    return friendID;
   }
   public String toString(){
    String toReturn = "";
    toReturn = toReturn.concat("Grade: "+this.grade+" - ");
    toReturn = toReturn.concat("Year: "+this.year+" - ");
    toReturn = toReturn.concat("Name: "+this.name+" - ");
    toReturn = toReturn.concat("Height: "+this.height+" - ");
    toReturn = toReturn.concat("id: "+this.id+" - ");
    toReturn = toReturn.concat("medicalFrontPreference: "+this.medicalFrontPreference+" - ");
    toReturn = toReturn.concat("medicalBackPreference: "+this.medicalBackPreference+" - ");
    toReturn = toReturn.concat("friendID: "+this.friendID);
    return toReturn;
   }
  public void setGrade(double grade) {
    this.grade = grade;
  }
  public void setYear(int year) {
    this.year = year;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setHeight(double height) {
    this.height = height;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setMedicalFrontPreference(boolean medicalFrontPreference) {
    this.medicalFrontPreference = medicalFrontPreference;
  }
  public void setMedicalBackPreference(boolean medicalBackPreference) {
    this.medicalBackPreference = medicalBackPreference;
  }
  public void setFriendID(String friendID) {
    this.friendID = friendID;
  }
   
 }
