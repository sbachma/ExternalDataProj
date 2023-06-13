public class Student {
    protected String fname;
    protected String lname;
    protected int year;
    protected double gpa;
    protected boolean java;

    public Student() {
        this.fname = "";
        this.lname = "";
        this.year = 0;
        this.gpa = 0.00;
        this.java = false;
    }

    public Student(String fname, String lname, int year, double gpa, boolean java) {
        this.fname = fname;
        this.lname = lname;
        this.year = year;
        this.gpa = gpa;
        this.java = java;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean getJava() {
        return java;
    }
    public void setJava(boolean java) {
        this.java = java;
    }



    @Override
    public String toString() {
        return "Student{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", year=" + year +
                ", gpa=" + gpa +
                ", java=" + java +
                '}';
    }
}
