package USECASE;

import BEAN.Course;
import DAO.AdminDao;
import DAO.AdminDaoImpl;
import DAO.StudentDao;
import DAO.StudentDaoImpl;
import UTILITY.DBUtility;

public class Main {

	public static void main(String[] args) {


		StudentDao dao=new StudentDaoImpl();

		try {
		  dao.AvailableCourseSeat();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
