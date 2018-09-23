package sv.com.ventaboletos.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.jvides.models.dao.ICustomerDao;
import sv.com.jvides.models.entities.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao customerDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Customer> findAll() {		
		return (List<Customer>) customerDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Customer findOne(Long id) {		
		return customerDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		customerDao.deleteById(id);
	}
}
