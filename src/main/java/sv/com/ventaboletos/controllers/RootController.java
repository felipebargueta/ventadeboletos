package sv.com.ventaboletos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("title","Sistema de Gestion y Venta de Boletos");
		return "index";
	}
}
