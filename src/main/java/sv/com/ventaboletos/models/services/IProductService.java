package sv.com.ventaboletos.models.services;

import java.util.List;

import sv.com.jvides.models.entities.Product;

public interface IProductService {
	
	public List<Product> findAll();
	
	public void save(Product product);
	
	public Product findOne(Long id);
	
	public void delete(Long id);
}
