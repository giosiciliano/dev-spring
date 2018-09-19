package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Course;
import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;


public class EagerLazyDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("instructor.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
		
			// start a transaction
			session.beginTransaction();
			
			// get the instructor from the db
			int theId = 1;
			Instructor tempIns = session.get(Instructor.class, theId);
			
			System.out.println("GIO: Instructor: " + tempIns);
			
			System.out.println("GIO: Courses: " + tempIns.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\nGIO: The session is now closed!\n");
			
			// option 1: call getter method while session is open
			
			// get courses from instructor
			System.out.println("GIO: Courses: " + tempIns.getCourses());
			
			System.out.println("GIO: Done!");
			
		} finally {
			// add cleanup code
			session.close();
			factory.close();
		}

	}

}
