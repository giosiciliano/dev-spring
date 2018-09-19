package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Course;
import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;
import com.gio.entity.Review;
import com.gio.entity.Student;


public class DeleteMaryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
		
			// start a transaction
			session.beginTransaction();
			
			// get the student mary from database
			Student mary = session.get(Student.class, 2);
			System.out.println("\nLoaded student: " + mary);
			System.out.println("Courses: " + mary.getCourses());
			
			// delete student
			System.out.println("\nDeleting student: " + mary);
			session.delete(mary);
			
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
