package it.epicode.be.energy.controller.rest;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.service.FatturaService;

@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "bearerAuth")
public class FatturaController {

	@Autowired
	FatturaService fatturaServ;

	@GetMapping(path = "/fatturastato/{stato}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Trova le fatture in base allo 'stato'")
	public ResponseEntity<Page<Fattura>> findByStato(@PathVariable(required = true) String stato, Pageable pageable) {
		Page<Fattura> findByStato = fatturaServ.findByStato(stato, pageable);

		if (findByStato.hasContent()) {
			return new ResponseEntity<>(findByStato, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/fattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisce una nuova fattura nel Db")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		Fattura save = fatturaServ.save(fattura);
		return new ResponseEntity<>(save, HttpStatus.CREATED);

	}

	@DeleteMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina una fattura dal Db")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		fatturaServ.delete(id);
		return new ResponseEntity<>("Fattura cancellata!", HttpStatus.OK);

	}

	@PutMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna una fattura presente nel Db")
	public ResponseEntity<Fattura> update(@PathVariable Long id, @RequestBody Fattura fattura) {
		Fattura update = fatturaServ.update(id, fattura);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@GetMapping(path = "/fatturadata")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca fatture in base alla data")
	public ResponseEntity<Page<Fattura>> findByData(
			@RequestParam(value = "time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			Pageable pageable) {
		Page<Fattura> findByData = fatturaServ.findByData(date, pageable);

		if (findByData.hasContent()) {
			return new ResponseEntity<>(findByData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/fatturaidcliente/{idCliente}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca fatture in base all'id del cliente")
	public ResponseEntity<Page<Fattura>> findFatturaByClienteId(@PathVariable(required = true) Long idCliente,
			Pageable pageable) {
		Page<Fattura> findByIdCliente = fatturaServ.findFatturaByClienteId(idCliente, pageable);

		if (findByIdCliente.hasContent()) {
			return new ResponseEntity<>(findByIdCliente, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/fatturaanno/{anno}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca fatture in base all'anno")
	public ResponseEntity<Page<Fattura>> findByAnno(@PathVariable(required = true) int anno, Pageable pageable) {
		Page<Fattura> findByAnno = fatturaServ.findByAnno(anno, pageable);

		if (findByAnno.hasContent()) {
			return new ResponseEntity<>(findByAnno, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/fatturarange/{min}/{max}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca fatture in base al range di importi (tra min e max)")
	public ResponseEntity<Page<Fattura>> findByRange(@PathVariable(required = true) BigDecimal min,
			@PathVariable(required = true) BigDecimal max, Pageable pageable) {
		Page<Fattura> findByRange = fatturaServ.findByRange(min, max, pageable);

		if (findByRange.hasContent()) {
			return new ResponseEntity<>(findByRange, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/fatture")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista di tutte le fatture presenti nel Db")
	public ResponseEntity<Page<Fattura>> findAll(Pageable pageable) {

		Page<Fattura> find = fatturaServ.findAll(pageable);
		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

}
