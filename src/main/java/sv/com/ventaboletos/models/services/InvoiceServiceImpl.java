package sv.com.ventaboletos.models.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sv.com.jvides.models.dao.IInvoiceDao;
import sv.com.jvides.models.entities.Invoice;

@Component("invoiceService")
public class InvoiceServiceImpl implements IInvoiceService {

	@Autowired
	private IInvoiceDao invoiceDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Invoice> findAll() {
		return (List<Invoice>) invoiceDao.findAll();
	}

	@Override
	@Transactional()
	public void save(Invoice invoice) {
		invoiceDao.save(invoice);		
	}

	@Override
	@Transactional(readOnly=true)
	public Invoice findOne(Long id) {
		return invoiceDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		invoiceDao.deleteById(id);		
	}
}
