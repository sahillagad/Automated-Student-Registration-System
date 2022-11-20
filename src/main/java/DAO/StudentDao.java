package DAO;

import BEAN.Student;
import Exception.*;

public interface StudentDao {

    public boolean StudentLogin(String email,String Password) throws StudentException;
    public String UpdateProfile(int roll) throws  StudentException;
    public void  AvailableCourseSeat() throws  BatchException;


}
