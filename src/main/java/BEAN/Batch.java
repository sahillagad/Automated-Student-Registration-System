package BEAN;

public class Batch {

  private  int batchId;
  private  String batchName;
  private  int numberOfSeat;
  private int batchCurrStud;
  private  int courseId;

    public Batch() {
    }

    public Batch(int batchId, String batchName, int numberOfSeat, int batchCurrStud, int courseId) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.numberOfSeat = numberOfSeat;
        this.batchCurrStud = batchCurrStud;
        this.courseId = courseId;
    }

    public Batch(String batchName, int numberOfSeat, int batchCurrStud, int courseId) {
        this.batchName = batchName;
        this.numberOfSeat = numberOfSeat;
        this.batchCurrStud = batchCurrStud;
        this.courseId = courseId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public int getBatchCurrStud() {
        return batchCurrStud;
    }

    public void setBatchCurrStud(int batchCurrStud) {
        this.batchCurrStud = batchCurrStud;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", numberOfSeat=" + numberOfSeat +
                ", batchCurrStud=" + batchCurrStud +
                ", courseId=" + courseId +
                '}';
    }
}
