package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.epicode.be.energy.service.web.FatturaServiceWeb;

@Controller
@RequestMapping("/fatturebootstrap")
public class FatturaControllerWebBootStrap {

	private final FatturaServiceWeb fatturaServWeb;

	@Autowired
	public FatturaControllerWebBootStrap(FatturaServiceWeb fatturaServWeb) {
		this.fatturaServWeb = fatturaServWeb;
	}

	@GetMapping("/mostraelenco")
	public String fatture(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size, Model model) {
		model.addAttribute("posts", fatturaServWeb.getPage(pageNumber, size));
		return "elencofatturebootstrap";
	}

}
