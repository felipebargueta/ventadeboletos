package sv.com.ventaboletos.models.dao;

import org.springframework.data.repository.CrudRepository;

import sv.com.jvides.models.entities.Invoice;

public interface IInvoiceDao extends CrudRepository<Invoice, Long> {}
