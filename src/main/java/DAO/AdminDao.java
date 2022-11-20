package DAO;

import BEAN.Batch;
import BEAN.Course;
import BEAN.Student;
import  Exception.*;
public interface AdminDao {

    public  String addCourse(Course course) throws CourseException;
    public  String createBatch(Batch batch) throws BatchException;
    public String CreateStudent(Student student) throws  StudentException;
    public  String updateCourseFees(int Courseid,int CourseFees) throws  CourseException;
    public  Course courseDetail(int CourseId) throws  CourseException;
    public  String allotStudentBatch(int Roll,int BatchId) throws  BatchException ,StudentException,BatchStudentException;
    public  String updateTotalSeats(int BatchId,int UpdateSeat) throws  BatchException;
    public  void viewAllStudent() throws StudentException;
    public  void  viewAllStudentByBatch(int BatchId) throws  BatchException ,BatchStudentException;
    public  String deleteCurse(int CourseId) throws  CourseException;
    public  String deleteBatch(int Batch) throws BatchException;
    public String deleteStudent(int StudentID) throws StudentException;



}
