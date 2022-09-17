package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.epicode.be.energy.service.web.ProvinciaServiceWeb;

@Controller
@RequestMapping("/provincebootstrap")
public class ProvinciaControllerWebBootStrap {

	private final ProvinciaServiceWeb provinciaServWeb;

	@Autowired
	public ProvinciaControllerWebBootStrap(ProvinciaServiceWeb provinciaServWeb) {
		this.provinciaServWeb = provinciaServWeb;
	}

	@GetMapping("/mostraelenco")
	public String comuni(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size, Model model) {
		model.addAttribute("posts", provinciaServWeb.getPage(pageNumber, size));
		return "elencoprovincebootstrap";
	}

}
