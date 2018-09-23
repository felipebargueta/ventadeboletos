package sv.com.ventaboletos.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.com.jvides.models.entities.Product;
import sv.com.jvides.models.entities.Product;
import sv.com.jvides.models.services.IProductService;

@Controller
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private IProductService productService;
	
	@RequestMapping(value="/products" ,method=RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("title", "Productos");
		model.addAttribute("products", productService.findAll());
		return "products";
	}
	
	@RequestMapping(value="/product")
	public String crear(Map<String, Object> model) {
		Product product = new Product();
		model.put("product", product);
		model.put("title", "Formulario de Productos");
		return "product";
	}
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String guardar(@Valid Product product, BindingResult bindingResult, RedirectAttributes flash, SessionStatus sessionStatus ) {
		if(bindingResult.hasErrors()) {
			return "products";
		}
		
		productService.save(product);
		sessionStatus.setComplete();
		
		flash.addFlashAttribute("success","Producto creado con exito");
		return "redirect:products";
	}
	
	
	@RequestMapping(value="/product/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Product product = null;
		if (id > 0) {
			product = productService.findOne(id);
		}else {
			flash.addFlashAttribute("error","El Id del producto no puede ser cero");
			return "redirect:/products";
		}
		model.put("product", product);
		model.put("title", "Editar Producto");
		
		return "product";
	}
	
	@RequestMapping(value="/remove/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if (id > 0 ) {
			productService.delete(id);
		}
		flash.addFlashAttribute("success","producto eliminado con exito");
		return "redirect:/products";
	}
	
	@RequestMapping(value="/products/regresar/")
	public String regresar() {
		return "redirect:/products";
	}
	
	
}
