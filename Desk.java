<<<<<<< HEAD
class Desk {
    private boolean occupied;
    private Student student;
    private boolean isChecked = false;
  
    public Desk() {
        occupied = false;
        this.student = new Student();
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
        Student result = student;
        this.student = null;
        this.occupied = false;
        return result;
    }
  
    public boolean getChecked() {
        return isChecked;
    }
  
    public void setChecked(boolean b) {
        isChecked = b;
    }
  }
=======
public class Desk {
  private boolean occupied;
  private Student student;
  private boolean isChecked = false;

  public Desk() {
      occupied = false;
      this.student = new Student();
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
      Student result = student;
      this.student = null;
      this.occupied = false;
      return result;
  }

  public boolean getChecked() {
      return isChecked;
  }

  public void setChecked(boolean b) {
      isChecked = b;
  }
}
>>>>>>> 931fc12f4d1d3abf608730fb37fc73d285d7a5e0
