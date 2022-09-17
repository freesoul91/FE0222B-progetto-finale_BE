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
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.service.ComuneService;
import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/comuni")
public class ComuneControllerWeb {

	@Autowired
	ComuneService comuneServ;

	@Autowired
	ProvinciaService provinciaServ;

	@GetMapping("/mostraelenco")
	@Operation(description = "Mostra elenco comuni su pagina Thymeleaf")
	public ModelAndView mostraElencoComuni(Pageable pageable) {
		log.info("Elenco comuni su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencocomuni");
		view.addObject("listaComuni", comuneServ.findAll(pageable));
		return view;
	}

	@GetMapping("/mostraformaggiungi")
	public String mostraFormAggiungi(Comune comune, Model model) {
		log.info("form aggiunta comune");
		model.addAttribute("listaProvince", provinciaServ.findAll());
		return "formComune";
	}

	@PostMapping("/addComune")
	public String aggiungiComune(@Valid Comune comune, BindingResult result, Model model) {
		log.info("Action aggiunta comune");
		if (result.hasErrors()) {
			model.addAttribute("listaProvince", provinciaServ.findAll());
			return "formCliente";
		}
		comuneServ.save(comune);
		return "redirect:/comuni/mostraelenco";
	}

	@GetMapping("/eliminacomune/{id}")
	public ModelAndView eliminaComune(@PathVariable Long id, Model model) {
		Optional<Comune> comuneTempElim = comuneServ.findById(id);
		if (comuneTempElim.isPresent()) {
			comuneServ.delete(id);
			ModelAndView view = new ModelAndView("elencocomuni");
			view.addObject("listaComuni", comuneServ.findAll());
			return view;
		} else {
			return new ModelAndView("error").addObject("message", "Comune con id " + id + " non trovato!");
		}

	}

}
