package com.devhive.propiedaddirect.web.app.controllers;

import com.devhive.propiedaddirect.web.app.models.services.IAgentService;
import com.devhive.propiedaddirect.web.app.models.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Value("${application.controllers.titulo}")
	private String titulo;

	@Autowired
	private IPropertyService propertyService;

	@Autowired
	private IAgentService agentsService;
	
	@GetMapping({"","/", "/index", "/home"})
	public String index(Model model) {
		model.addAttribute("titulo", titulo);
		model.addAttribute("properties", propertyService.findAll());
		model.addAttribute("agents", agentsService.findAll());
		return "index";
	}
	
	@GetMapping("/about")
	public String acerca(Model model) {
		model.addAttribute("agents", agentsService.findAll());
		model.addAttribute("titulo", titulo);
		return "about";
	}
	
	@GetMapping("/blog-grid")
	public String blogGrid(Model model) {
		model.addAttribute("titulo", titulo);
		return "blogs/blog-grid";
	}
	
	@GetMapping("/blog-single")
	public String blogSingle(Model model) {
		model.addAttribute("titulo", titulo);
		return "blogs/blog-single";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("titulo", titulo);
		return "contact";
	}
	

}
