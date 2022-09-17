package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.epicode.be.energy.service.web.IndirizzoServiceWeb;

@Controller
@RequestMapping("/indirizzibootstrap")
public class IndirizzoControllerWebBootStrap {

	private final IndirizzoServiceWeb indirizzoServWeb;

	@Autowired
	public IndirizzoControllerWebBootStrap(IndirizzoServiceWeb indirizzoServWeb) {
		this.indirizzoServWeb = indirizzoServWeb;
	}

	@GetMapping("/mostraelenco")
	public String indirizzi(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "size", required = false, defaultValue = "20") int size, Model model) {
		model.addAttribute("posts", indirizzoServWeb.getPage(pageNumber, size));
		return "elencoindirizzibootstrap";
	}

}
