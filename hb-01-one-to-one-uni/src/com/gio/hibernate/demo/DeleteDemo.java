package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;
import com.gio.entity.Student;

public class DeleteDemo {

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
		
			// get instructor by primary key/id
			int theId = 1;
			Instructor ins = 
					session.get(Instructor.class, theId);
			
			System.out.println("Found Instructor: " + ins);
			
			// delete the instructors
			if (ins != null) {
				System.out.println("Deleting instructor: " + ins);
				// Note: will ALSO delete associated "details" objects
				// because of CascadeType.ALL
				session.delete(ins);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
