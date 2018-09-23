package sv.com.ventaboletos.models.services;

import java.util.List;

import sv.com.jvides.models.entities.Invoice;

public interface IInvoiceService {

	public List<Invoice> findAll();
	
	public void save(Invoice invoice);
	
	public Invoice findOne(Long id);
	
	public void delete(Long id);
}
