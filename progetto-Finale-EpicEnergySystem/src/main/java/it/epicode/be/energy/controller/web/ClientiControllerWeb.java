package it.epicode.be.energy.controller.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.service.ClienteService;
import it.epicode.be.energy.service.ComuneService;
import it.epicode.be.energy.service.FatturaService;
import it.epicode.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/clienti")
public class ClientiControllerWeb {

	@Autowired
	ClienteService clienteServ;

	@Autowired
	IndirizzoService indirizzoServ;

	@Autowired
	ComuneService comuneServ;

	@Autowired
	FatturaService fatturaServ;

	@GetMapping("/mostraelenco")
	@Operation(description = "Mostra elenco clienti su pagina Thymeleaf")
	public ModelAndView mostraElencoClienti(Pageable pageable) {
		log.info("Elenco clienti su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoclienti");
		view.addObject("listaClienti", clienteServ.findAll(pageable));
		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Cliente cliente, Indirizzo sedLeg, Indirizzo sedOpe, Model model) {
		log.info("form aggiunta cliente");
		model.addAttribute("listaComuni", comuneServ.findAll());
		return "formCliente";
	}

	@PostMapping("/addCliente")
	public String aggiungiCliente(@Valid Cliente cliente, Indirizzo indirizzoSedLeg, Indirizzo indirizzoSedOpe,
			BindingResult result, Model model) {
		log.info("Action aggiunta cliente");
		if (result.hasErrors()) {
			model.addAttribute("listaComuni", comuneServ.findAll());
			return "formCliente";
		}

		clienteServ.save(cliente);

		return "redirect:/clienti/mostraelenco";
	}

	@GetMapping("/eliminacliente/{id}")
	public ModelAndView eliminaCliente(@PathVariable Long id, Model model) {
		Optional<Cliente> clienteTempElim = clienteServ.findById(id);
		if (clienteTempElim.isPresent()) {
			clienteServ.delete(id);
			ModelAndView view = new ModelAndView("elencoclienti");
			view.addObject("listaClienti", clienteServ.findAll());
			return view;
		} else {
			return new ModelAndView("error").addObject("message", "Cliente con id " + id + " non trovato!");
		}

	}

	@GetMapping("/mostraformfattura")
	public String mostraFormAggiungiFattura(Cliente cliente, Fattura fattura, Model model) {
		log.info("form aggiunta fattura");
		model.addAttribute("listaClienti", clienteServ.findAll());
		return "formFattura";
	}

	@PostMapping("/addFattura")
	public String aggiungiFattura(@Valid Fattura fattura, Cliente cliente, BindingResult result, Model model) {
		log.info("Action aggiunta fattura");
		if (result.hasErrors()) {
			model.addAttribute("listaClienti", clienteServ.findAll());
			return "formFattura";
		}

		fatturaServ.save(fattura);

		return "redirect:/clienti/mostraelencofatture";
	}

	@GetMapping("/mostraelencofatture")
	@Operation(description = "Mostra elenco fatture su pagina Thymeleaf")
	public ModelAndView mostraElencoFatture(Pageable pageable) {
		log.info("Elenco fatture su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencofatture");
		view.addObject("listaFatture", fatturaServ.findAll(pageable));
		return view;
	}

	@GetMapping("/eliminafattura/{id}")
	public ModelAndView eliminaFattura(@PathVariable Long id, Model model) {
		Optional<Fattura> fatturaTempElim = fatturaServ.findById(id);
		if (fatturaTempElim.isPresent()) {
			fatturaServ.delete(id);
			ModelAndView view = new ModelAndView("elencofatture");
			view.addObject("listaFatture", fatturaServ.findAll());
			return view;
		} else {
			return new ModelAndView("error").addObject("message", "Fattura con id " + id + " non trovata!");
		}

	}

	@GetMapping("/eliminaindirizzo/{id}")
	public ModelAndView eliminaIndirizzo(@PathVariable Long id, Model model) {
		Optional<Indirizzo> indirizzoTempElim = indirizzoServ.findById(id);
		if (indirizzoTempElim.isPresent()) {
			indirizzoServ.delete(id);
			ModelAndView view = new ModelAndView("elencoindirizzibootstrap");
			view.addObject("listaIndirizzi", indirizzoServ.findAll());
			return view;
		} else {
			return new ModelAndView("error").addObject("message", "Indirizzo con id " + id + " non trovato!");
		}

	}

}
