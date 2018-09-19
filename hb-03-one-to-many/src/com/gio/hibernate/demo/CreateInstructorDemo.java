package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Course;
import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;


public class CreateInstructorDemo {

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
		
			// create the objects
			Instructor tempInstructor = 
					new Instructor("Susan", "Public", "suzie.public@gio.com");
			
			InstructorDetail tempInsDetail = 
					new InstructorDetail("http://www.games.com/",
							"gamer");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInsDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			// note: this will ALSO save the details object
			// because CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
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
