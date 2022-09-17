package it.epicode.be.energy.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.service.ComuneService;
import it.epicode.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/indirizzi")
public class IndirizzoControllerWeb {

	@Autowired
	IndirizzoService indirizzoServ;

	@Autowired
	ComuneService comuneServ;

	@GetMapping("/mostraelenco")
	@Operation(description = "Mostra elenco indirizzi su pagina Thymeleaf")
	public ModelAndView mostraElencoIndirizzi(Pageable pageable) {
		log.info("Elenco indirizzi su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoindirizzi");
		view.addObject("listaIndirizzi", indirizzoServ.findAll());
		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Indirizzo indirizzo, Model model) {
		log.info("form aggiunta indirizzo");
		model.addAttribute("listaComuni", comuneServ.findAll());
		return "formIndirizzo";
	}

	@PostMapping("/addIndirizzo")
	public String aggiungiIndirizzo(@Valid Indirizzo indirizzo, BindingResult result, Model model) {
		log.info("Action aggiunta indirizzo");
		if (result.hasErrors()) {
			model.addAttribute("listaComuni", comuneServ.findAll());
			return "formIndirizzo";
		}
		indirizzoServ.save(indirizzo);
		return "redirect:/indirizzi/mostraelenco";
	}

}
