package sv.com.ventaboletos.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.com.jvides.models.entities.Customer;
import sv.com.jvides.models.entities.Invoice;
import sv.com.jvides.models.services.ICustomerService;
import sv.com.jvides.models.services.IInvoiceService;
import sv.com.jvides.models.services.IProductService;

@Controller
public class InvoiceController {

	@Autowired
	@Qualifier("invoiceService")
	private IInvoiceService invoiceService;
	
	@Autowired
	private ICustomerService customerService;
	
	//@Qualifier("invoiceService")	
	//private IProductService prouductService;
	
	
	@RequestMapping(value="/invoices" ,method=RequestMethod.GET)
	public String invoices(Model model) {
		model.addAttribute("title","listado de facturas");
		model.addAttribute("invoices", invoiceService.findAll());
		return "invoices";
	}
	
	@RequestMapping(value="/invoice/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Invoice invoice = null;
		List<Customer> customers = new ArrayList<Customer>();
		if (id > 0) {
			invoice = invoiceService.findOne(id);
			System.out.println(customerService.findOne(invoice.getCustomer().getId()));
			customers.add(customerService.findOne(invoice.getCustomer().getId()));			
		}else {
			flash.addFlashAttribute("error","El Id de factura no puede ser cero");
			return "redirect:/invoices";
		}
		
		model.put("invoice", invoice);
		model.put("customers", customers);
		model.put("title", "Ver Factura");
		model.put("readonly", "true");
		
		return "invoice";
	}
	

}
