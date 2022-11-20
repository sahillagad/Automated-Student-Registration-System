package BEAN;

import java.time.LocalDate;

public class StudentCourse {

    private int roll;
    private String  name;
    private int marks;
    private String email;
    private int batchId;
    private int courseId;
    private int courseName;
    private String Duration;

    public StudentCourse(int roll, String name, int marks, String email, int batchId, int courseId, int courseName, String duration) {
        this.roll = roll;
        this.name = name;
        this.marks = marks;
        this.email = email;
        this.batchId = batchId;
        this.courseId = courseId;
        this.courseName = courseName;
        Duration = duration;
    }



    public StudentCourse() {
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseName() {
        return courseName;
    }

    public void setCourseName(int courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "roll=" + roll +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                ", email='" + email + '\'' +
                ", batchId=" + batchId +
                ", courseId=" + courseId +
                ", courseName=" + courseName +
                ", Duration='" + Duration + '\'' +
                '}';
    }
}
