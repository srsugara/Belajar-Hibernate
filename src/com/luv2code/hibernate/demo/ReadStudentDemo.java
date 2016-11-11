package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			//create a student object
			System.out.println("Creating new student object");
			Student tempStudent=new Student("Adit", "Putra", "utama@gmail.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			//my new code
			System.out.println("Saved student. Generated id :"+tempStudent.getId());
			
			//now get new session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student base on the id
			System.out.println("Getting student with id : "+tempStudent.getId());
			
			Student myStudent=session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get completem : "+myStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			factory.close();
		}
	}

}
