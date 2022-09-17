package it.epicode.be.energy.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.service.IndirizzoService;

@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "bearerAuth")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoServ;

	@GetMapping(path = "/indirizzo")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca di TUTTI gli indirizzi nel db")
	public ResponseEntity<Page<Indirizzo>> findAll(Pageable pageable) {
		Page<Indirizzo> findAll = indirizzoServ.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/indirizzo/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca degli indirizzi per id")
	public ResponseEntity<Indirizzo> findById(@PathVariable(required = true) Long id) {
		Optional<Indirizzo> find = indirizzoServ.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping(path = "/indirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Cancellazione degli indirizzi per id")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		indirizzoServ.delete(id);
		return new ResponseEntity<>("Indirizzo cancellato!", HttpStatus.OK);

	}

	@PostMapping(path = "/indirizzo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserimento nuovo indirizzo nel Db")
	public ResponseEntity<Indirizzo> save(@RequestBody Indirizzo indirizzo) {
		Indirizzo save = indirizzoServ.save(indirizzo);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/indirizzo/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Modifica indirizzo per id")
	public ResponseEntity<Indirizzo> update(@PathVariable Long id, @RequestBody Indirizzo indirizzo) {
		Indirizzo update = indirizzoServ.update(id, indirizzo);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

}
