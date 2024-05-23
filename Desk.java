public class Desk {
<<<<<<< HEAD
  private boolean occupied;
  private Student student;
  private boolean isChecked = false;

  public Desk() {
      occupied = false;
      this.student = null;
  }

  public boolean getOccupied() {
      return occupied;
  }

  public void setOccupied() {
      this.occupied = true;
  }

  public void seat(Student s) {
      this.student = s;
      setOccupied();
      setChecked(true);
  }

  public Student getStudent() {
      return student;
  }

  public Student unseat() {
=======
   private boolean occupied;
   private Student student;
   private boolean isChecked = false;
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
    setChecked(true);
   }
   public Student getStudent(){
    return student;
   }
   public Student unseat(){
>>>>>>> ec788062c9569434337ddf03f71066d219121b4d
      Student result = student;
      this.student = null;
      this.occupied = false;
      return result;
<<<<<<< HEAD
  }

  public boolean getChecked() {
      return isChecked;
  }

  public void setChecked(boolean b) {
      isChecked = b;
  }
}
=======
   }
   public boolean getChecked(){
    return isChecked;
   }
   public void setChecked(boolean b){
    isChecked = b;
   }
    }
>>>>>>> ec788062c9569434337ddf03f71066d219121b4d
