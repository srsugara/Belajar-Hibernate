package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create session factory
		SessionFactory factory=new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		try {
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents=session.createQuery("from Student").list();
			//display students
			displayStudents(theStudents);
			
			//query students : lastname="Rahmat"
			theStudents=session.createQuery("from Student where lastName='Rahmat' OR firstName='Rahmat'").list();		
			//display students
			System.out.println("\nStudents who have lastname or firstname is Rahmat");
			displayStudents(theStudents);
			
			//query where email LIKE %gmail
			theStudents=session.createQuery("from Student where email LIKE '%gmail%'").list();		
			//display students
			System.out.println("\nStudents who have email gmail");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

}
