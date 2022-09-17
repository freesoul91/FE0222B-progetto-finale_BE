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
import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.service.ComuneService;

@RestController
@RequestMapping(path = "/api")
@SecurityRequirement(name = "bearerAuth")
public class ComuneController {

	@Autowired
	ComuneService comuneServ;

	@GetMapping(path = "/comune")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca di TUTTI i comuni italiani")
	public ResponseEntity<Page<Comune>> findAll(Pageable pageable) {
		Page<Comune> findAll = comuneServ.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/comune/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Ricerca dei comuni italiani per id")
	public ResponseEntity<Comune> findById(@PathVariable(required = true) Long id) {
		Optional<Comune> find = comuneServ.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping(path = "/comune/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Cancellazione dei comuni italiani per id")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		comuneServ.delete(id);
		return new ResponseEntity<>("Comune cancellato!", HttpStatus.OK);

	}

	@PostMapping(path = "/comune")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserimento nuovo comune nel Db")
	public ResponseEntity<Comune> save(@RequestBody Comune comune) {
		Comune save = comuneServ.save(comune);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/comune/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Modifica dei comuni italiani per id")
	public ResponseEntity<Comune> update(@PathVariable Long id, @RequestBody Comune comune) {
		Comune update = comuneServ.update(id, comune);
		return new ResponseEntity<>(update, HttpStatus.OK);

	}

}
