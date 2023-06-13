import java.util.Objects;
import java.util.Scanner;
import java.sql.*;


public class UserInterface {
    static Scanner s = new Scanner(System.in);
    static Connection conn = null;

    public static void main(String[] args) {
        connect();
        String menu = "y";
        while(menu.equals("y")){
            create();
            System.out.println("Do you want to create another?(y/n)");
            menu = s.nextLine();
        }
        selectAll();
    }
    private static void create() {
        Student student = makeStudent();
        try{
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Student (fname, lname, year, gpa, java) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setString(1, student.getFname());
            ps.setString(2, student.getLname());
            ps.setInt(3, student.getYear());
            ps.setDouble(4, student.getGpa());
            ps.setBoolean(5, student.getJava());
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    private static Student makeStudent() {
        Student student = new Student();
        System.out.println("Enter data for new student");
        System.out.println("Enter first name: ");
        student.setFname(s.nextLine());
        System.out.println("Enter last name: ");
        student.setLname(s.nextLine());
        System.out.println("Enter year: ");
        student.setYear(Integer.parseInt(s.nextLine()));
        System.out.println("Enter gpa: ");
        student.setGpa(Double.parseDouble(s.nextLine()));
        System.out.println("Enter if they are in java or not(True/False): ");
        student.setJava(Boolean.parseBoolean(s.nextLine()));
        System.out.println("The new student data is: \n" + student);
        return student;
    }
    public static void connect(){
        try{
            String username = "root";
            String password = "Sb299319";
            String url = "jdbc:mysql://localhost:3306/students_winter2022";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection made");
        } catch (Exception e){
            System.out.println("Couldn't make db connection");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    public static void selectAll(){
        Statement s;
        try{
            s = conn.createStatement();
            s.execute("SELECT * FROM STUDENT");
            ResultSet rs = s.getResultSet();
            while(rs.next()){
                Student student = new Student(rs.getString("fname"),
                        rs.getString("lname"),
                        Integer.parseInt(rs.getString("year")),
                        Double.parseDouble(rs.getString("gpa")),
                        Boolean.parseBoolean(rs.getString("java")));
                System.out.println("\nStudent ID "+ rs.getString("id") + ", ");
                System.out.println(student);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error during select");
            e.printStackTrace();
        }
    }
}
