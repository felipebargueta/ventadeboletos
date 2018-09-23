package sv.com.ventaboletos.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.com.jvides.models.entities.Customer;
import sv.com.jvides.models.services.ICustomerService;

@Controller
@SessionAttributes("customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("title","listado de cliente");
		model.addAttribute("customers", customerService.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> model) {
		Customer customer = new Customer();
		model.put("customer", customer);
		model.put("title", "Formulario de cliente");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Customer customer, BindingResult bindingResult, RedirectAttributes flash, SessionStatus sessionStatus ) {
		if(bindingResult.hasErrors()) {
			return "form";
		}
		customerService.save(customer);
		sessionStatus.setComplete();
		
		flash.addFlashAttribute("success","Cliente creado con exito");
		return "redirect:listar";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Customer customer = null;
		if (id > 0) {
			customer = customerService.findOne(id);
		}else {
			flash.addFlashAttribute("error","El Id del cliente no puede ser cero");
			return "redirect:/listar";
		}
		model.put("customer", customer);
		model.put("title", "Editar Cliente");
		
		return "form";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if (id > 0 ) {
			customerService.delete(id);
		}
		flash.addFlashAttribute("success","Cliente eliminado con exito");
		return "redirect:/listar";
	}
	
	@RequestMapping(value="/ver/{id}", method=RequestMethod.GET)
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flashl) {
		Customer customer = customerService.findOne(id);
		if (customer == null) {
			flashl.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("customer", customer);
		model.put("titulo", "Editar Cliente");
		return "ver";
	}
	
	
}
