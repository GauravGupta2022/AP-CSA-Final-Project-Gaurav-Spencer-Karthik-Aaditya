public class Desk {
   private boolean occupied;
   private Student student;
   public Desk(){
     occupied = false;
     this.student = null;
   }
   public boolean getOccupied(){
     return occupied;
   }
   public void setOccupied(){
     this.occupied = true;
   }
   public void seat(Student s){
    this.student = s;
    setOccupied();
   }
   public Student getStudent(){
    return student;
   }
   public Student unseat(){
      Student result = student;
      this.student = null;
      this.occupied = false;
      return result;
   }
    }
