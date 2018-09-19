package com.gio.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gio.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", 
						Customer.class);
		
		// execute and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save or update the Customer based on id passed
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// get row based on primary key and return
		return currentSession.get(Customer.class, theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete customer
		//currentSession.delete(getCustomer(theId));
		// another way
		  Query theQuery =
		  		currentSession.createQuery("delete from Customer where id=:customerId");
		  theQuery.setParameter("customerId", theId);
		  theQuery.executeUpdate();
		 
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		// only search name if not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for firstName or lastName case insensitive
			theQuery = currentSession.createQuery("from Customer where "
					+ "lower(firstName) like :name or lower(lastName) like :name",
					Customer.class);
			theQuery.setParameter("name", "%" + theSearchName.toLowerCase() + "%");
		} else {
			// empty so return all customers
			theQuery = currentSession.createQuery("from Customer", Customer.class);
		}
		
		// execute query and return result list
		return theQuery.getResultList();
	}
	

}
