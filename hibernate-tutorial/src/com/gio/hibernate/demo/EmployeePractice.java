package com.gio.hibernate.demo;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gio.entity.Employee;

public class EmployeePractice {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("employee.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a employee object
			System.out.println("Creating a new employee object...");
			Employee tempEmployee = new Employee("Russel", "Westbrook", "Thunder");
			
			// start a transaction
			session.beginTransaction();
			
			// save the employee object
			System.out.println("Saving the employee...");
			session.save(tempEmployee);
			
			// commit transaction
			session.getTransaction().commit();
			
			// retrieve object by primary key
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Employee newEmployee = session.get(Employee.class, tempEmployee.getId());
			System.out.println("Read new employee: " + newEmployee);
			
			// retrieve all emps with company like %s
			List<Employee> emps = session.createQuery("from Employee e where e.company"
					+ " LIKE '%s'").getResultList();
			
			for (Employee employee : emps) {
				System.out.println(employee);
			}
			
			// commit transaction
			session.getTransaction().commit();
			
			// randomly delete 1 employee 
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			Random r = new Random();
			int empToDel = r.nextInt((emps.size() - 1) + 1) + 1;
						
			System.out.println("Employee out of here: " + emps.get(empToDel));
			session.delete(emps.get(empToDel));
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}

	}

}
