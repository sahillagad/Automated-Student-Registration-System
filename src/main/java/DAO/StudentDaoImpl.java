package DAO;

import BEAN.Student;
import UTILITY.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import Exception.*;
import dnl.utils.text.table.TextTable;

public class StudentDaoImpl implements StudentDao {


    @Override
    public boolean StudentLogin(String email, String Password) throws StudentException {
        boolean b = false;

        try (Connection conn = DBUtility.provideconnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from student where email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, Password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                b = true;
            } else {
                StudentException studentException = new StudentException("Invalid Email Address And Password...");
                throw studentException;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return b;
    }


    @Override
    public String UpdateProfile(int roll) throws StudentException {

        String result = "Student Profile Update Process failed";


        try (Connection conn = DBUtility.provideconnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from student where roll=?");
            ps.setInt(1, roll);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int roll1 = resultSet.getInt("roll");
                String name = resultSet.getString("name");
                int marks = resultSet.getInt("marks");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Student student = new Student(roll, name, marks, email, password);

                while (true) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Enter 1 For Update Name" +
                            "\nEnter 2 For Update Marks" +
                            "\nEnter 3 For Update Email" +
                            "\nEnter 4 For Update Password" +
                            "\nEnter 5 For Exit");

                    int output = sc.nextInt();

                    if (output == 1) {
                        System.out.println("Enter Update Name");
                        String sName = sc.next();

                        student.setName(sName);

                    }
                    if (output == 2) {
                        System.out.println("Enter Update Marks");
                        int sMarks = sc.nextInt();
                        student.setMarks(sMarks);
                    }
                    if (output == 3) {
                        System.out.println("Enter Update Email");
                        String sEmail = sc.next();
                        student.setEmail(sEmail);

                    }
                    if (output == 4) {

                        System.out.println("Enter Update Password");
                        String sPassword = sc.next();
                        student.setPassword(sPassword);
                    }
                    if (output == 5) {

                        PreparedStatement ps5 = conn.prepareStatement("Update student set name=?,marks=?,email=?,password=? where roll=? ");
                        ps5.setString(1, student.getName());
                        ps5.setInt(2, student.getMarks());
                        ps5.setString(3, student.getEmail());
                        ps5.setString(4, student.getPassword());
                        int x = ps5.executeUpdate();
                        if (x > 0) {
                            result = "Student Profile Update Process successfully...";
                        } else {

                            StudentException studentException = new StudentException("Student Update Process Failed Due To Same Error...");
                            throw studentException;

                        }
                        break;
                    }

                }


            } else {

                StudentException studentException = new StudentException("Student IS  Not Found By Given Roll");


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    @Override
    public void AvailableCourseSeat()  throws  BatchException{

        try (Connection conn=DBUtility.provideconnection()){


                PreparedStatement ps5 = conn.prepareStatement("select count(*) from batch b where b.batchCurrStud  NOT LIKE b.numberOfSeat");
                ResultSet rs5 = ps5.executeQuery();
                rs5.next();
                int count = rs5.getInt(1);

            String[] columnNames = {
                    "batchId",
                    "batchName",
                    "course_Id",
                    "courseName",
                    "Duration",
                    "Startdate",
                    "courseTeacher"
            };

            Object[][] data = new Object[count][7];
            int index = 0;


            if(count>0) {

                    PreparedStatement ps = conn.prepareStatement("select * from batch b Inner Join Course c ON b.course_Id=c.courseId where  b.batchCurrStud  NOT LIKE b.numberOfSeat");
                    ResultSet resultSet = ps.executeQuery();

                    while (resultSet.next()) {
                        int batchId = resultSet.getInt("batchId");
                        String batchString = resultSet.getString("batchName");
                        int courseId = resultSet.getInt("course_Id");
                        String CourseName = resultSet.getString("courseName");
                        String Duration = resultSet.getString("Duration");
                        LocalDate date = resultSet.getDate("Startdate").toLocalDate();
                        String courseTeacher = resultSet.getString("courseTeacher");


                        Object[] obj = {batchId, batchString, courseId, CourseName, Duration, date, courseTeacher};
                        data[index] = obj;
                        index = index + 1;

                    }
                    TextTable tt = new TextTable(columnNames, data);
                    tt.setAddRowNumbering(true);
                    tt.setSort(0);
                    tt.printTable();


                }
                else{

                    BatchException batchException=new BatchException("Seat is Not Available any Batch ");
                    throw  batchException;

                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}