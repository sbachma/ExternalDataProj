import java.sql.*;

public class CreateDatabase {
    static Connection conn = null;
    public static void main(String[] args) {
        createDatabase();
        connect();
        createTable();
        selectAll();
    }

    //Change persons to students

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

    public static void createTable(){
        Statement s;
        try {
            s = conn.createStatement();
            conn.setAutoCommit(false);
            s.executeUpdate("DROP TABLE IF EXISTS student");
            String createStatement = ("CREATE TABLE STUDENT(ID INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                    "PRIMARY KEY(ID)," +
                    "fname VARCHAR(10)," +
                    "lname VARCHAR(10)," +
                    "year INT," +
                    "gpa DOUBLE," +
                    "java BOOLEAN)");
            System.out.println(createStatement);
            s.executeUpdate(createStatement + "ENGINE = innoDB");
            conn.commit();
            s.executeUpdate("INSERT INTO STUDENT(fname, lname, year, gpa, java)" +
                    "VALUES " +
                    "('Sam', 'Bachman', 2, 4.00, True)");
            s.executeUpdate("INSERT INTO STUDENT(fname, lname, year, gpa, java)" +
                    "VALUES " +
                    "('Jonah', 'West', 1, 1.50, False)");
            conn.setAutoCommit(true);
        }catch(SQLException e) {
            System.out.println("SQL Error during DROP/CREATE");
            e.printStackTrace();
        }
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
    public static void createDatabase(){
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
        try {
            Statement statement = conn.createStatement();
            String sql = null;
            sql = "DROP DATABASE IF EXISTS STUDENTS_WINTER2022";
            statement.executeUpdate(sql);
            sql = "CREATE DATABASE STUDENTS_WINTER2022";
            statement.executeUpdate(sql);
            System.out.println("Database made");
        } catch (Exception e){
            System.err.println("Couldn't create database");
            System.err.println(e.getMessage());
        }
    }
}