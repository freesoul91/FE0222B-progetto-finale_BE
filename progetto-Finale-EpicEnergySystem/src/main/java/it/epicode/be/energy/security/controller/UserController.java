package it.epicode.be.energy.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import it.epicode.be.energy.security.model.User;
import it.epicode.be.energy.security.service.UserService;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Trova utente per id")
	public ResponseEntity<User> findById(@PathVariable(required = true) Long id) {
		Optional<User> find = userService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/users")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(description = "Lista di TUTTI gli Utenti presenti nel DB")
	public ResponseEntity<List<User>> findAll() {
		List<User> findAll = userService.findAll();

		if (findAll != null) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/user")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Inserimento Utente (operazione consentita SOLO all'Admin)")
	public ResponseEntity<User> save(@RequestBody User user) {
		User save = userService.save(user);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Update Utente per id Utente (operazione consentita SOLO all'Admin)")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		User save = userService.update(id, user);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(description = "Delete Utente per id Utente (operazione consentita SOLO all'Admin)")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		userService.delete(id);
		return new ResponseEntity<>("Utente deleted", HttpStatus.OK);

	}

}
