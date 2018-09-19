package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.gio.entity.Course;
import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;


public class FetchJoinDemo {

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
			
			// option 2: Hibernate query with HQL
			
			// get the instructor from the db
			int theId = 1;
			
			Query<Instructor> query =
					session.createQuery("select i from Instructor i "
							+ " JOIN FETCH i.courses "
							+ "where i.id = :theInstructorId", 
							Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId",  theId);
			
			// execute query and get instructor
			Instructor tempIns = query.getSingleResult();
			
			System.out.println("GIO: Instructor: " + tempIns);
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\nGIO: The session is now closed!\n");
			
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
