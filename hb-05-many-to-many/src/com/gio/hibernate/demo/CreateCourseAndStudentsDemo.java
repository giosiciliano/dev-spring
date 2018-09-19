package com.gio.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Course;
import com.gio.entity.Instructor;
import com.gio.entity.InstructorDetail;
import com.gio.entity.Review;
import com.gio.entity.Student;


public class CreateCourseAndStudentsDemo {

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
			
			// create a course
			Course tempCourse = new Course("Pacman - How to Score One Million Points");
			
			// save the course
			System.out.println("\nSaving the course ...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
	
			// create the students
			Student tempStu1 = new Student("John", "Doe", "john@gio.com");
			Student tempStu2 = new Student("Mary", "Publlic", "mary@gio.com");
			
			// add students to the course
			tempCourse.addStudent(tempStu1);
			tempCourse.addStudent(tempStu2);
			
			// save the students
			System.out.println("\nSaving students ...");
			session.save(tempStu1);
			session.save(tempStu2);
			System.out.println("Saved students: " + tempCourse.getStudents());
			
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
