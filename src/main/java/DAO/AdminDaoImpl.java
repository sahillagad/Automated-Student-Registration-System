package DAO;

import BEAN.Batch;
import BEAN.Course;
import BEAN.Student;
import Exception.*;
import UTILITY.DBUtility;
import dnl.utils.text.table.TextTable;
import java.sql.*;
import java.time.LocalDate;

public class AdminDaoImpl implements AdminDao{


    @Override
    public String addCourse(Course course) throws CourseException {
        String result="Course IS Not Created....";

        try (Connection conn= DBUtility.provideconnection()){

            PreparedStatement ps1=conn.prepareStatement("select * from course where courseName=?");
           ps1.setString(1,course.getCourseName());
             ResultSet rs= ps1.executeQuery();

             if(rs.next()==false) {
                 PreparedStatement ps = conn.prepareStatement("insert into course(courseName,fee,Duration,Startdate,courseTeacher) values(?,?,?,?,?)");
                 ps.setString(1, course.getCourseName());
                 ps.setInt(2, course.getFee());
                 ps.setString(3, course.getDuration());
                 ps.setDate(4, Date.valueOf(course.getStartDate()));
                 ps.setString(5, course.getCourseTeacher());

                 int x = ps.executeUpdate();
                 System.out.println("Course Creation Process is Start....");

                 if (x > 0) {

                     result = "Course Created Successfully...";

                 } else {
                     CourseException courseException = new CourseException("Course Is Created Process Is Failed Due To Some Error...");
                  throw  courseException;
                 }

             }
             else {
                 CourseException courseException1=new CourseException("This Course Is Already Present...");
                 throw  courseException1;

             }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return result;
    }

    @Override
    public String createBatch(Batch batch) throws BatchException {

        String result="Batch Creation Failed...";

        try (Connection conn=DBUtility.provideconnection()){

            PreparedStatement ps=conn.prepareStatement("select * from batch where batchName=?");
            ps.setString(1,batch.getBatchName());

            ResultSet rs= ps.executeQuery();
            if(rs.next()==false){

                PreparedStatement ps1=conn.prepareStatement("insert into batch(batchName,course_Id,batchCurrStud,numberOfSeat) values(?,?,?,?)");
                ps1.setString(1,batch.getBatchName());
                ps1.setInt(2,batch.getCourseId());
                 ps1.setInt(3,batch.getBatchCurrStud());
                 ps1.setInt(4,batch.getNumberOfSeat());

                 int i=ps1.executeUpdate();

                  if (i>0){
                      result="Batch is Created Successfully...";
                  }
                  else {

                      BatchException batchException1=new BatchException("Batch Is Created Process Is Failed Due To Some Error...");
                      throw  batchException1;
                  }


            }else{
                BatchException batchException=new BatchException("This Batch IS Already Present...");
                throw batchException;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

      return  result;
    }

    @Override
    public String CreateStudent(Student student) throws StudentException {
     String  result="Student Is Not Created...";

     try(Connection conn=DBUtility.provideconnection()) {
         PreparedStatement ps= conn.prepareStatement("select * from Student where name=? AND email=? ");
         ps.setString(1,student.getName());
         ps.setString(2,student.getEmail());
         ResultSet rs = ps.executeQuery();

         if(rs.next()==false){

             PreparedStatement ps1=conn.prepareStatement("insert into Student(name,marks,email,password) values(?,?,?,?)");
                ps1.setString(1,student.getName());
                ps1.setInt(2,student.getMarks());
                ps1.setString(3,student.getEmail());
                ps1.setString(4,student.getPassword());


               int x= ps1.executeUpdate();

             System.out.println("Student Insert Process Start...");
               if(x>0){

                   result="Student Added Successfully...";

               }
               else{

                   StudentException studentException1=new StudentException("Student Is Created Process Is Failed Due To Some Error...");
                   throw  studentException1;
               }

         }

         else{
             StudentException studentException=new StudentException("This Student is Already Present...");
            throw studentException;
         }






     } catch (SQLException e) {
         throw new RuntimeException(e);
     }


        return result;
    }

    @Override
    public String updateCourseFees(int Courseid, int CourseFees) throws CourseException {
        String result="Course Is Fee Update Failed...";

        try (Connection conn=DBUtility.provideconnection() ){

            PreparedStatement ps=conn.prepareStatement("select * from course where courseId=?");
            ps.setInt(1,Courseid);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){

                PreparedStatement ps1=conn.prepareStatement("Update course set fee=? where courseId=?");
                ps1.setInt(1,CourseFees);
                ps1.setInt(2,Courseid);
                int x= ps1.executeUpdate();
                System.out.println("Course Update Process Failed...");
              if(x>0){

                  result="Course Fee Update Successfully....";

              }
              else{

                  CourseException courseException1=new CourseException("Course Is Update Process Is Failed Due To Some Error...");
                  throw  courseException1;

              }
            }
            else {

                CourseException courseException=new CourseException("Course is Not Available by Given This CourseId...");
                throw  courseException;

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

         return result;
    }

    @Override
    public Course courseDetail(int CourseId) throws CourseException {
        Course course=null;


        try(Connection conn=DBUtility.provideconnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from course where courseId=?");
            ps.setInt(1, CourseId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int courseId=resultSet.getInt("courseId");
                String courseName=resultSet.getString("courseName");
                int courseFee=resultSet.getInt("fee");
                String courseDuration=resultSet.getString("duration");
                LocalDate date= resultSet.getDate("Startdate").toLocalDate();
                String courseTeacher=resultSet.getString("courseTeacher");

                course=new Course(courseId,courseName,courseFee,courseDuration,date,courseTeacher);



            }
            else {

                CourseException courseException=new CourseException("Course Is Not Available By Given Course ID ");
                throw  courseException;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return course;
    }

    @Override
    public String allotStudentBatch(int Roll, int BatchId) throws BatchException, StudentException ,BatchStudentException{

        String result="Course Not Allotted...";

        try(Connection conn=DBUtility.provideconnection()) {

            PreparedStatement ps=conn.prepareStatement("select * from batch where batch_Id=?");
            ps.setInt(1,BatchId);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){

                PreparedStatement ps1=conn.prepareStatement("Select * from student where roll=?");
                ps1.setInt(1,Roll);
                ResultSet rs1=ps1.executeQuery();

                if(rs1.next()){

                    PreparedStatement ps2=conn.prepareStatement("insert into Batch_student values(?,?)");
                     ps2.setInt(1,BatchId);
                     ps2.setInt(2,Roll);

                    int x= ps2.executeUpdate();

                     if(x>0){
                         result="Batch Allottment Process Successfully...";
                     }
                     else{
                         BatchStudentException batchStudentException=new BatchStudentException("Batch Allottment Process Is Failed Due To Some Error...");
                         throw batchStudentException;
                     }
                }
                else {

                    StudentException studentException=new StudentException("Student Is Not Available by Given  Roll...");
                    throw studentException;


                }
            }
            else{

                BatchException batchException=new BatchException("Batch Is Not Available by Given  BatchId...");
               throw  batchException;

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return result;
    }

    @Override
    public String updateTotalSeats(int BatchId, int UpdateSeat) throws BatchException {


        String result="Batch Number Of Seat is  Not Update...";

        try(Connection conn=DBUtility.provideconnection()) {

            PreparedStatement ps = conn.prepareStatement("select * from batch where batchId=?");
            ps.setInt(1, BatchId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                PreparedStatement ps1 = conn.prepareStatement("update batch set numberOfSeat=? where batchId=?");
                ps1.setInt(1, UpdateSeat);
                ps1.setInt(2,BatchId);
                int x= ps1.executeUpdate();


                 if(x>0){

                     result="Batch Number Of Seat is Update Successfully...";

                 }
                 else {
                     BatchException batchException=new BatchException("Batch Update Process Failed Due To Some Error...");
                     throw  batchException;
                 }
            }
            else {

                BatchException batchException1=new BatchException("Batch Is Not Found  By given batch Id...");
                 throw  batchException1;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

     return result;
    }

    @Override
    public void viewAllStudent() throws StudentException {
        try(Connection conn=DBUtility.provideconnection()) {
            PreparedStatement ps5 = conn.prepareStatement("select count(*) from batch_student");
            ResultSet rs5 = ps5.executeQuery();
            rs5.next();
            int count = rs5.getInt(1);


            if(count>0) {

                PreparedStatement ps1 = conn.prepareStatement("Select * from student");
                ResultSet resultSet = ps1.executeQuery();




                   String[] columnNames = {
                           "roll",
                           "name",
                           "marks",
                           "email"
                   };

                   Object[][] data = new Object[count][4];
                   int index = 0;

                   while (resultSet.next()) {

                       int roll = resultSet.getInt("roll");
                       String name = resultSet.getString("name");
                       int marks=resultSet.getInt("marks");
                       String email=resultSet.getString("email");




                       Object[] obj = {roll,name, marks,email};
                       data[index] = obj;
                       index = index + 1;
                   }

                   TextTable tt = new TextTable(columnNames, data);
                   tt.setAddRowNumbering(true);
                   tt.setSort(0);
                   tt.printTable();








            }
            else{

                StudentException exception=new StudentException("Student data base is Empty..");
                throw  exception;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewAllStudentByBatch(int BatchId) throws BatchException,BatchStudentException {

        try(Connection conn=DBUtility.provideconnection()) {

            PreparedStatement ps=conn.prepareStatement("select * from batch where batchId=?");
            ps.setInt(1,BatchId);

            ResultSet rs=ps.executeQuery();

            if(rs.next()) {


                PreparedStatement ps5 = conn.prepareStatement("select count(*) from batch_student");
                ResultSet rs5 = ps5.executeQuery();
                rs5.next();
                int count = rs5.getInt(1);


                if(count>0) {


                    PreparedStatement ps1 = conn.prepareStatement("Select  S.roll,s.name,s.marks,s.email,bs.batch_Id,c.courseId,c.courseName,c.duration from batch_student bs INNER Join  student s ON  bs.roll_no = s.roll Inner Join  batch b ON bs.batch_Id=b.batchId Inner Join Course c ON b.course_Id=c.courseId where bs.batch_Id=?");
                    ps1.setInt(1, BatchId);
                    ResultSet resultSet = ps1.executeQuery();


                    String[] columnNames = {
                            "roll",
                            "name",
                            "marks",
                            "email",
                            "batch_Id",
                            "courseId",
                            "courseName",
                            "duration"
                    };

                    Object[][] data = new Object[count][8];
                    int index = 0;

                    while (resultSet.next()) {

                        int roll = resultSet.getInt("roll");
                        String name = resultSet.getString("name");
                        int marks=resultSet.getInt("marks");
                        String email=resultSet.getString("email");
                        int batchId=resultSet.getInt("batch_Id");
                        int courseId=resultSet.getInt("courseId");
                        String courseName=resultSet.getString("courseName");
                        String courseDuration=resultSet.getString("duration");



                        Object[] obj = {roll,name, marks,email,batchId,courseId ,courseName,courseDuration};
                        data[index] = obj;
                        index = index + 1;
                    }

                    TextTable tt = new TextTable(columnNames, data);
                    tt.setAddRowNumbering(true);
                    tt.setSort(0);
                    tt.printTable();

                }
                else if(count==0) {

                    BatchStudentException batchStudentException = new BatchStudentException("Batch Seat is Empty...");
                    throw batchStudentException;

                }


            }
            else{


                BatchException batchException=new BatchException("Batch Is Not Available by Given  BatchId...");
                throw  batchException;
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteCurse(int CourseId) throws CourseException {
     String result="Course Delete Process Failed...";


        try(Connection conn=DBUtility.provideconnection()) {

            PreparedStatement ps=conn.prepareStatement("select * from course where courseId=?");
            ps.setInt(1,CourseId);
            ResultSet rs=ps.executeQuery();

            if(rs.next()) {
                PreparedStatement ps1=conn.prepareStatement("delete from course where courseId=?");
                ps1.setInt(1,CourseId);
                int x=ps1.executeUpdate();

                 if(x>0){
                     result="Course Delete Successfully....";
                 }
                 else{
                      CourseException courseException=new CourseException("Course Delete Process Failed...");
                      throw courseException;
                 }
            }else{
                CourseException courseException=new CourseException("Course Is  Not Found By Given Course Id...");
                throw  courseException;
            }

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  result;
    }

    @Override
    public String deleteBatch(int BatchId) throws BatchException {
        String result="Batch Delete Process Failed...";


        try(Connection conn=DBUtility.provideconnection()) {

            PreparedStatement ps=conn.prepareStatement("select * from batch where batchId=?");
            ps.setInt(1,BatchId);
            ResultSet rs=ps.executeQuery();

            if(rs.next()) {
                PreparedStatement ps1=conn.prepareStatement("delete from batch where batchId=?");
                ps1.setInt(1,BatchId);
                int x=ps1.executeUpdate();

                if(x>0){
                    result="Batch Delete Successfully....";
                }
                else{
                    BatchException batchException=new BatchException("Batch Delete Process Failed...");
                    throw batchException;
                }
            }else{
                BatchException batchException=new BatchException("Batch Is  Not Found By Given Batch Id...");
                throw  batchException;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  result;
    }

    @Override
    public String deleteStudent(int roll) throws StudentException {
        String result="Student Delete Process Failed...";


        try(Connection conn=DBUtility.provideconnection()) {

            PreparedStatement ps=conn.prepareStatement("select * from student where roll=?");
            ps.setInt(1,roll);
            ResultSet rs=ps.executeQuery();

            if(rs.next()) {
                PreparedStatement ps1=conn.prepareStatement("delete from student where roll=?");
                ps1.setInt(1,roll);
                int x=ps1.executeUpdate();

                if(x>0){
                    result="Student Delete Successfully....";
                }
                else{
                    StudentException studentException=new StudentException("Student Delete Process Failed...");
                    throw studentException;
                }
            }else{
                StudentException studentException1=new StudentException("Student Is  Not Found By Given Batch Id...");
                throw  studentException1;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  result;
    }
}
