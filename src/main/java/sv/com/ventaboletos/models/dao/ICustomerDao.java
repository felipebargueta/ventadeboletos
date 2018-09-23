package sv.com.ventaboletos.models.dao;

import org.springframework.data.repository.CrudRepository;

import sv.com.jvides.models.entities.Customer;


public interface ICustomerDao extends CrudRepository<Customer, Long> {
	
	//CrudRepository implementa todas las operaciones basicas de un crud.
	
	//@Query("select u from Customer u where u.email = ?1")
	//public Customer findByEmailAddress(String email);
}
