package BEAN;

import java.time.LocalDate;

public class Course {

     private int courseId;
     private String courseName;
     private int fee;
     private  String duration;
     private LocalDate startDate;
     private String courseTeacher;

    public Course(int courseId, String courseName, int fee, String duration, LocalDate startDate, String courseTeacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.fee = fee;
        this.duration = duration;
        this.startDate = startDate;
        this.courseTeacher = courseTeacher;
    }

    public Course(String courseName, int fee, String duration, LocalDate startDate, String courseTeacher) {
        this.courseName = courseName;
        this.fee = fee;
        this.duration = duration;
        this.startDate = startDate;
        this.courseTeacher = courseTeacher;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", fee=" + fee +
                ", duration='" + duration + '\'' +
                ", startDate=" + startDate +
                ", courseTeacher='" + courseTeacher + '\'' +
                '}';
    }
}
