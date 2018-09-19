package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Course;
import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;


public class CreateCoursesDemo {

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
			
			// create some courses
			Course tempCourse1 = new Course("Air Guitar - Ultimate Guide");
			Course tempCourse2 = new Course("Pinball Masterclass");
			
			// add courses to instructor
			tempIns.add(tempCourse1);
			tempIns.add(tempCourse2);
			
			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
	
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			// add cleanup code
			session.close();
			factory.close();
		}

	}

}
