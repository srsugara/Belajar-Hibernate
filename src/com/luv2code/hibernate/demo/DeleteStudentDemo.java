package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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
			int studentId=5;
			session.beginTransaction();
			
			//retrieve student base on the id
			System.out.println("Getting student with id : "+studentId);
			
			Student myStudent=session.get(Student.class, studentId);
			if(myStudent==null){
				System.out.println("Student is null");
			}
			else{
				System.out.println("Deleting student : "+myStudent.getFirstName());
				session.delete(myStudent);
				//commit transaction
			}		
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			factory.close();
		}
	}

}
