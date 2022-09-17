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
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;

@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

	@Autowired
	ClienteService clienteServ;

	@PostMapping(path = "/cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserisce un nuovo cliente nel Db")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente save = clienteServ.save(cliente);
		return new ResponseEntity<>(save, HttpStatus.CREATED);

	}

	@DeleteMapping(path = "/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Elimina un cliente dal Db")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		clienteServ.delete(id);
		return new ResponseEntity<>("Cliente cancellato con successo!", HttpStatus.OK);

	}

	@PutMapping(path = "/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Aggiorna un cliente presente nel Db")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente update = clienteServ.update(id, cliente);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

	@GetMapping(path = "/clientesortedfatturatoannuale/{fattAnn}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca clienti in base all'anno ed ordinamento in base al fatturato annuale")
	public ResponseEntity<Page<Cliente>> findAllSortedByFatturatoAnnuale(
			@PathVariable(required = true) BigDecimal fattAnn, Pageable pageable) {
		Page<Cliente> find = clienteServ.findAllSortedByFatturatoAnnuale(fattAnn, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/clienti")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca di tutti i clienti con paginazione")
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		Page<Cliente> find = clienteServ.findAll(pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/clientedataultimocontatto")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca clienti in base alla data ultimo contatto")
	public ResponseEntity<Page<Cliente>> findAllByDataUltimoContatto(
			@RequestParam(value = "time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			Pageable pageable) {

		Page<Cliente> find = clienteServ.findByDataUltimoContatto(date, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/clientedatainserimento")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca clienti in base alla data di inserimento")
	public ResponseEntity<Page<Cliente>> findAllByDataInserimento(
			@RequestParam(value = "time", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
			Pageable pageable) {

		Page<Cliente> find = clienteServ.findByDataInserimento(date, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/clienteparteragionesociale/{ragSoc}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca clienti in base alla ragione sociale (anche parziale)")
	public ResponseEntity<Page<Cliente>> findByRagioneSocialeContaining(@PathVariable(required = true) String ragSoc,
			Pageable pageable) {
		Page<Cliente> find = clienteServ.findByParteRagioneSociale(ragSoc, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/clienteprovincia/{siglaProvincia}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca clienti in base alla sigla della provincia")
	public ResponseEntity<Page<Cliente>> findAllByProvinciaSigla(@PathVariable(required = true) String siglaProvincia,
			Pageable pageable) {
		Page<Cliente> find = clienteServ.findAllByProvinciaSigla(siglaProvincia, pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/allclienteorderbynomecontatto")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca di tutti i clienti con ordinamento alfabetico")
	public ResponseEntity<Page<Cliente>> findByOrderByNomeContattoAsc(Pageable pageable) {
		Page<Cliente> find = clienteServ.findByOrderByNomeContattoAsc(pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/allclienteorderbyfatturatoannuale")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca di tutti i clienti con ordinamento in base al fatturato annuale (discendente)")
	public ResponseEntity<Page<Cliente>> findByOrderByFatturatoAnnualeDesc(Pageable pageable) {
		Page<Cliente> find = clienteServ.findByOrderByFatturatoAnnualeDesc(pageable);

		if (find.hasContent()) {
			return new ResponseEntity<>(find, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

}
