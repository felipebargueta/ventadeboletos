package sv.com.ventaboletos.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sv.com.jvides.models.dao.IProductDao;
import sv.com.jvides.models.entities.Product;

@Component("productService")
public class ProductServiceImp implements IProductService {
	
	@Autowired
	private IProductDao productDao;

	@Override
	@Transactional(readOnly=true)
	public List<Product> findAll() {
		return (List<Product>)productDao.findAll();
	}

	@Override
	@Transactional()
	public void save(Product product) {
		productDao.save(product);		
	}

	@Override
	@Transactional(readOnly=true)	
	public Product findOne(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		productDao.deleteById(id);		
	}

}
