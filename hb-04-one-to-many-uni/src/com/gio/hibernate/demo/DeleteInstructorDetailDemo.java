package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
		
			// start a transaction
			session.beginTransaction();
		
			// get the instructor detail object
			int theId = 3;
			InstructorDetail tempInsDet = session.get(InstructorDetail.class, theId);
			
			// print the instructor detail
			System.out.println("Instructor Detail: " + tempInsDet);
			
			// print the associated instructor
			System.out.println("Associated instructors: " + tempInsDet.getInstructor());
			
			// remove the associated object reference
			// break bi-directional link
			tempInsDet.getInstructor().setInstructorDetail(null);
			
			// delete the instructor detail
			System.out.println("Deleting InstructorDetail: " + tempInsDet);
			session.delete(tempInsDet);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch (Exception e) { 
			e.printStackTrace();
		}	
		finally {
			// handle connection leaks
			session.close();
			factory.close();
		}

	}

}
