package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
			int studentId=1;
			session.beginTransaction();
			
			//retrieve student base on the id
			System.out.println("Getting student with id : "+studentId);
			
			Student myStudent=session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Rivki");
			
			//commit transaction
			session.getTransaction().commit();
			
			//create session
			session=factory.getCurrentSession();
			session.beginTransaction();
			//update email with HQL
			System.out.println("Update email with query");
			session.createQuery("update Student set email='srsugara@mail.unpas.ac.id' where id=4").executeUpdate();
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		} finally {
			factory.close();
		}
	}

}
